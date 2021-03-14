package asl.model.core;

public abstract class NumericAtom<T extends Number> extends Atom<T> {
    protected NumericAtom(T value) {
        super(value);
    }
}
