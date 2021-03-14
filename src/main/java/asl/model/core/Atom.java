package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Базовый тип для элементов, которые не могут иметь атрибутов
 */
public abstract class Atom<T> extends ASLObject {
    protected final T value;

    protected Atom(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public @NotNull Atom<T> evaluate(Context context) {
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
