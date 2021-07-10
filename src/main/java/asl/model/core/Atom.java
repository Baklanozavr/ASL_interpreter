package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/** Base type for all elements without attributes */
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

    /** Atoms are immutable */
    @Override
    public @NotNull Atom<T> copyShallow() {
        return this;
    }

    @Override
    public @NotNull Atom<T> copyDeep() {
        return this;
    }

    @Override
    public boolean equalsShallow(ASLObject o) {
        return equals(o);
    }

    @Override
    public boolean equalsDeep(ASLObject o) {
        return equals(o);
    }

    @Override
    public boolean equalsLink(ASLObject o) {
        return equals(o);
    }

    @Override
    public @NotNull String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atom<?> atom = (Atom<?>) o;
        return value.equals(atom.value);
    }
}
