package asl.model.core;

import org.jetbrains.annotations.NotNull;

public class Attribute {
    public final ASLObject key;

    public Attribute(@NotNull ASLObject key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return key instanceof ASLObjectWithAttributes ?
                key == attribute.key :
                key.equals(attribute.key);
    }

    @Override
    public int hashCode() {
        return key instanceof ASLObjectWithAttributes ?
                System.identityHashCode(key) :
                key.hashCode();
    }
}
