package asl.model.util;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.Attribute;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AttributonHelper {
    private final ASLObjectWithAttributes root;
    private final Set<ASLObjectWithAttributes> onceVisitedObjects = Collections.newSetFromMap(new IdentityHashMap<>());
    private final Set<ASLObjectWithAttributes> visitedRefs = Collections.newSetFromMap(new IdentityHashMap<>());
    private final Map<ASLObjectWithAttributes, Integer> references = new IdentityHashMap<>();
    private final Map<Integer, ASLObjectWithAttributes> refCopies = new HashMap<>();
    private int refsCounter = 0;

    public AttributonHelper(ASLObjectWithAttributes root) {
        this.root = root;
        visit(root);
    }

    public ASLObjectWithAttributes getDeepCopy() {
        return safeAttributonCopy(root);
    }

    public boolean hasSameStructureWith(ASLObjectWithAttributes o) {
        if (root.attributes.size() != o.attributes.size()) return false;

        var helper = new AttributonHelper(o);
        if (refsCounter != helper.refsCounter) return false;
        if (onceVisitedObjects.size() != helper.onceVisitedObjects.size()) return false;
        return areAttrEquals(root, o, helper);
    }

    private boolean areAttrEquals(ASLObjectWithAttributes local, ASLObjectWithAttributes o, AttributonHelper oHelper) {
        boolean localVisited = visitedRefs.contains(local);
        boolean oVisited = oHelper.visitedRefs.contains(o);

        if (localVisited && oVisited) return true;
        if (localVisited || oVisited) return false;
        if (references.containsKey(local)) {
            if (oHelper.references.containsKey(o)) {
                visitedRefs.add(local);
                oHelper.visitedRefs.add(o);
            } else return false;
        } else if (oHelper.references.containsKey(o)) return false;

        for (Map.Entry<Attribute, ASLObject> entry : local.attributes.entrySet()) {
            ASLObject attrKey = entry.getKey().key;
            ASLObject attrValue = entry.getValue();
            // todo: обработать случай ключа-объекта-с-аттрибутами
            ASLObject valueToCompare = o.get(attrKey);
            if (!areObjEquals(attrValue, valueToCompare, oHelper)) return false;
        }
        return true;
    }

    private boolean areObjEquals(ASLObject local, ASLObject o, AttributonHelper oHelper) {
        if (local == o) return true;

        if (local instanceof ASLObjectWithAttributes) {
            var localAttr = (ASLObjectWithAttributes) local;
            return toAttrOptional(o).filter(oAttr -> areAttrEquals(localAttr, oAttr, oHelper)).isPresent();
        } else if (toAttrOptional(o).isEmpty()) {
            return local.equalsDeep(o);
        }
        return false;
    }

    public String getString() {
        StringBuilder resultBuilder = new StringBuilder();

        Integer rootRef = references.get(root);
        // references.containsKey(root)
        boolean hasRootRef = rootRef != null;
        if (hasRootRef) {
            resultBuilder.append("#1 = ");
            // we want rootRef to be 1
            if (rootRef != 1) {
                ASLObjectWithAttributes firstRefObj = references.entrySet()
                        .stream()
                        .filter(entry -> 1 == entry.getValue())
                        .findFirst()
                        .map(Map.Entry::getKey)
                        .orElseThrow(() -> new IllegalStateException("No first ref!"));
                references.put(firstRefObj, rootRef);
                references.put(root, 1);
            }
        }

        resultBuilder.append(attributonString(root));

        int rootRefsNumber = hasRootRef ? 1 : 0;
        if (refsCounter > rootRefsNumber) {
            resultBuilder.append("\nwhere");
            references.entrySet()
                    .stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getValue))
                    .skip(rootRefsNumber)
                    .forEach(entry -> {
                        Integer refNumber = entry.getValue();
                        ASLObjectWithAttributes refRoot = entry.getKey();
                        resultBuilder
                                .append('\n')
                                .append('#')
                                .append(refNumber)
                                .append(" = ")
                                .append(attributonString(refRoot));
                    });
        }

        return resultBuilder.toString();
    }

    private String objectString(ASLObject aslObject) {
        return toAttrOptional(aslObject)
                .map(this::safeAttributonString)
                .orElseGet(aslObject::toString);
    }

    private String safeAttributonString(ASLObjectWithAttributes attributon) {
        Integer refNumber = references.get(attributon);
        return refNumber != null ?
                "^" + refNumber :
                attributonString(attributon);
    }

    private String attributonString(ASLObjectWithAttributes attributon) {
        // todo: fix case with two empty attributons as attribute keys
        Map<String, String> stringAttributes = attributon.getAttributes().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> objectString(e.getKey()),
                        e -> objectString(e.getValue()),
                        (r1, r2) -> {
                            throw new IllegalStateException("Unexpected same keys!");
                        },
                        TreeMap::new
                ));

        StringBuilder stringBuilder = new StringBuilder("{ ");
        stringAttributes.forEach((key, value) -> stringBuilder
                .append(key)
                .append(": ")
                .append(value)
                .append(", "));
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.length() == 0 ? "{}" : stringBuilder.append(" }").toString();
    }

    private ASLObjectWithAttributes safeAttributonCopy(ASLObjectWithAttributes attributon) {
        ASLObjectWithAttributes copy;
        Integer ref = references.get(attributon);
        if (ref != null) {
            copy = refCopies.get(ref);
            if (copy == null) {
                copy = attributon.copyDeepWithoutAttributes();
                refCopies.put(ref, copy);
                copyFromTo(attributon, copy);
            }
        } else {
            copy = attributon.copyDeepWithoutAttributes();
            copyFromTo(attributon, copy);
        }
        return copy;
    }

    private void copyFromTo(ASLObjectWithAttributes source, ASLObjectWithAttributes target) {
        if (refCopies.containsValue(source)) return;
        source.getAttributes().forEach((key, value) -> target.put(objectSafeCopy(key), objectSafeCopy(value)));
    }

    private ASLObject objectSafeCopy(ASLObject aslObject) {
        return toAttrOptional(aslObject)
                .map(this::safeAttributonCopy)
                .map(ASLObject.class::cast)
                .orElseGet(aslObject::copyDeep);
    }

    private void visit(ASLObjectWithAttributes attributon) {
        if (references.containsKey(attributon)) return;

        if (onceVisitedObjects.remove(attributon)) {
            ++refsCounter;
            references.put(attributon, refsCounter);
        } else {
            onceVisitedObjects.add(attributon);
        }

        for (Map.Entry<ASLObject, ASLObject> attr : attributon.getAttributes().entrySet()) {
            toAttrOptional(attr.getKey()).ifPresent(this::visit);
            toAttrOptional(attr.getValue()).ifPresent(this::visit);
        }
    }

    private Optional<ASLObjectWithAttributes> toAttrOptional(ASLObject aslObject) {
        return aslObject instanceof ASLObjectWithAttributes ?
                Optional.of((ASLObjectWithAttributes) aslObject) :
                Optional.empty();
    }
}
