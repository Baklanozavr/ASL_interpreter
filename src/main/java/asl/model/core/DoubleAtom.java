package asl.model.core;

public class DoubleAtom extends SyntaxAtom<Double> implements Numeric {
    public DoubleAtom(double value) {
        super(value);
    }

    @Override
    public Number number() {
        return value;
    }

    @Override
    public Numeric plus(Numeric other) {
        return new DoubleAtom(value + other.number().doubleValue());
    }

    @Override
    public Numeric minus(Numeric other) {
        return new DoubleAtom(value - other.number().doubleValue());
    }
}
