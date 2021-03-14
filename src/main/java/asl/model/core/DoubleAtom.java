package asl.model.core;

public final class DoubleAtom extends NumericAtom<Double> {
    public static DoubleAtom of(double value) {
        return new DoubleAtom(value);
    }

    private DoubleAtom(double value) {
        super(value);
    }
}
