package asl.model.core;

import java.util.Objects;

public abstract class SyntaxAtom<T> extends Atom {
    protected final T value;

    protected SyntaxAtom(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyntaxAtom)) return false;
        return value.equals(((SyntaxAtom<?>) o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + value + ")";
    }
}
