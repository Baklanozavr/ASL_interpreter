package asl.model.core;

import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Ordinary object with attributes.
 * Any element of this type could be treated as a sequence (see {@link asl.model.system.SequenceFacade})
 */
public class PlainAttributon extends ASLObjectWithAttributes {
    public PlainAttributon() {
    }

    public PlainAttributon(int size) {
        super(new HashMap<>(size));
    }

    private PlainAttributon(Map<ASLObject, ASLObject> attributes) {
        super(attributes);
    }

    public PlainAttributon put(@NotNull String attrName, ASLObject attrValue) {
        return (PlainAttributon) put(QNameAtom.create(attrName), attrValue);
    }

    public PlainAttributon setType(@NotNull ASLObject type) {
        return (PlainAttributon) put(type, BooleanAtom.TRUE);
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    /**
     * From Map's javadoc: "great care must be exercised if mutable objects are used as map keys..."
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map<ASLObject, ASLObject> oAttributes = ((PlainAttributon) o).attributes;
        if (attributes.size() != oAttributes.size()) return false;
        return attributes.entrySet().stream().allMatch(entry -> {
            ASLObject thisKey = entry.getKey();
            ASLObject oValue = oAttributes.get(thisKey);
            return oValue != null && oValue.equals(entry.getValue());
        });
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(
                attributes.entrySet().stream()
                        .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                        .toArray(ASLObject[]::new)
        );
    }

    @Override
    public @NotNull String toString() {
        Sequence sequenceCandidate = SequenceFacade.toSequence(this);
        return sequenceCandidate != null ?
                sequenceCandidate.toString() :
                attributonString();
    }

    public String attributonString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");
        attributes.forEach((key, value) -> {
            if (key != null && value != null)
                stringBuilder
                        .append(key.toString())
                        .append(" = ")
                        .append(value.toString())
                        .append(", ");
        });
        stringBuilder.setLength(stringBuilder.length() - 2);
        if (stringBuilder.length() == 0) return "{}";
        return stringBuilder.append(" }").toString();
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return this;
    }

    @Override
    public @NotNull PlainAttributon copyShallow() {
        return new PlainAttributon(attrsCopyShallow());
    }

    @Override
    public @NotNull PlainAttributon copyDeep() {
        return new PlainAttributon(attrsCopyDeep());
    }

    @Override
    public boolean equalsShallow(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsShallow);
    }

    @Override
    public boolean equalsDeep(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsDeep);
    }

    private boolean isEqualTo(ASLObject o, Predicate<Map<ASLObject, ASLObject>> compareFunction) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainAttributon attributon = (PlainAttributon) o;
        return compareFunction.test(attributon.attributes);
    }
}
