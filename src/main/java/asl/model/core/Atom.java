package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Базовый тип для элементов, которые не могут иметь атрибутов
 */
public abstract class Atom<T> extends ASLObject {
    @NotNull
    protected final T value;

    protected Atom(@NotNull T value) {
        this.value = value;
    }

    @NotNull
    public T value() {
        return value;
    }

    @Override
    public @NotNull Atom<T> evaluate(Context context) {
        return this;
    }

    /**
     * Atoms are immutable
     */
    @Override
    public @NotNull Atom<T> clone() {
        return this;
    }

    @Override
    public @NotNull String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atom<?> atom = (Atom<?>) o;
        return value.equals(atom.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
