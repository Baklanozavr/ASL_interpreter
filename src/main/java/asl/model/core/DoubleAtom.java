package asl.model.core;

import org.jetbrains.annotations.NotNull;

public class DoubleAtom extends SyntaxAtom<Double> implements Numeric {
    public DoubleAtom(double value) {
        super(value);
    }

    @Override
    public @NotNull Number number() {
        return value;
    }

    @Override
    public @NotNull Numeric plus(Numeric other) {
        return new DoubleAtom(value + other.number().doubleValue());
    }

    @Override
    public @NotNull Numeric minus(Numeric other) {
        return new DoubleAtom(value - other.number().doubleValue());
    }
}
