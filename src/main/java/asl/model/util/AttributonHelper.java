package asl.model.util;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;

import java.util.Collections;
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
    private final Map<ASLObjectWithAttributes, Integer> references = new IdentityHashMap<>();
    private final Map<Integer, ASLObjectWithAttributes> refCopies = new HashMap<>();
    private int counter = 0;

    public AttributonHelper(ASLObjectWithAttributes root) {
        this.root = root;
        visit(root);
    }

    public ASLObjectWithAttributes getDeepCopy() {
        return safeAttributonCopy(root);
    }

    public String getString() {
        StringBuilder resultBuilder = new StringBuilder();
        for (Map.Entry<ASLObjectWithAttributes, Integer> entry : references.entrySet()) {
            int refNumber = entry.getValue();
            ASLObjectWithAttributes refRoot = entry.getKey();
            resultBuilder
                    .append("#")
                    .append(refNumber)
                    .append(" = ")
                    .append(attributonString(refRoot))
                    .append(";\n");
        }
        if (references.containsKey(root)) {
            resultBuilder.setLength(resultBuilder.length() - 2);
        } else {
            resultBuilder.append(objectString(root));
        }
        return resultBuilder.toString();
    }

    private String objectString(ASLObject aslObject) {
        return toCandidate(aslObject)
                .map(this::safeAttributonString)
                .orElseGet(aslObject::toString);
    }

    private String safeAttributonString(ASLObjectWithAttributes attributon) {
        Integer refNumber = references.get(attributon);
        return refNumber != null ?
                "#" + refNumber :
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
        return toCandidate(aslObject)
                .map(this::safeAttributonCopy)
                .map(ASLObject.class::cast)
                .orElseGet(aslObject::copyDeep);
    }

    private void visit(ASLObjectWithAttributes attributon) {
        if (references.containsKey(attributon)) return;

        if (onceVisitedObjects.contains(attributon)) {
            ++counter;
            references.put(attributon, counter);
            onceVisitedObjects.remove(attributon);
        } else {
            onceVisitedObjects.add(attributon);
        }

        for (Map.Entry<ASLObject, ASLObject> attr : attributon.getAttributes().entrySet()) {
            toCandidate(attr.getKey()).ifPresent(this::visit);
            toCandidate(attr.getValue()).ifPresent(this::visit);
        }
    }

    private Optional<ASLObjectWithAttributes> toCandidate(ASLObject aslObject) {
        return aslObject instanceof ASLObjectWithAttributes ?
                Optional.of((ASLObjectWithAttributes) aslObject) :
                Optional.empty();
    }
}
