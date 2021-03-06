package asl.model.core;

import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Базовый тип для элементов, которые могут иметь атрибуты
 * <p>
 * Любой элемент данного типа может рассматриваться как последовательность
 */
public class PlainAttributon extends ASLObjectWithAttributes {
    public PlainAttributon() {
    }

    public PlainAttributon(int size) {
        super(new HashMap<>(size));
    }

    private PlainAttributon(PlainAttributon obj) {
        super(obj.attributes);
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

    /**
     * Строковое представление зависит от того, последовательность или нет
     */
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
    public @NotNull PlainAttributon clone() {
        return new PlainAttributon(this);
    }
}
