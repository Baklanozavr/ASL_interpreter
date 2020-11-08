package asl.model.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Базовый тип для элементов, которые могут иметь атрибуты
 */
public abstract class Attributon extends Thing {
    protected final Map<Thing, Thing> attributes = new HashMap<>();

    @Override
    public Thing get(Thing attribute) {
        return attributes.get(attribute);
    }

    public void setType(Thing type) {
        attributes.put(type, ASLBoolean.TRUE);
    }
}
