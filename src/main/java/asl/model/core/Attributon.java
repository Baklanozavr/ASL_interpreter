package asl.model.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Базовый тип для элементов, которые могут иметь атрибуты
 */
public class Attributon extends Thing {
    protected final Map<Thing, Thing> attributes = new HashMap<>();

    @Override
    public Thing get(Thing attribute) {
        return attributes.get(attribute);
    }

    public Attributon put(Thing attrKey, Thing attrValue) {
        attributes.put(attrKey, attrValue);
        return this;
    }

    public Attributon put(String attrName, Thing attrValue) {
        return put(QNameAtom.create(attrName), attrValue);
    }

    public Attributon put(String attrName, String attrValue) {
        return put(QNameAtom.create(attrName), QNameAtom.create(attrValue));
    }

    public Attributon setType(Thing type) {
        return put(type, BooleanAtom.TRUE);
    }

    /**
     * From Map's javadoc: "great care must be exercised if mutable objects are used as map keys..."
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var oAttributes = ((Attributon) o).attributes;
        if (attributes.size() != oAttributes.size()) return false;
        return attributes.entrySet().stream().allMatch(entry -> {
            Thing thisKey = entry.getKey();
            Thing oValue = oAttributes.get(thisKey);
            return oValue != null && oValue.equals(entry.getValue());
        });
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(attributes
                .entrySet()
                .stream()
                .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                .toArray(Thing[]::new));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        attributes.forEach((key, value) -> {
            if (key != null && value != null)
                stringBuilder
                        .append(key.toString())
                        .append(" = ")
                        .append(value.toString())
                        .append(",\n");
        });
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.append("\n}").toString();
    }
}
