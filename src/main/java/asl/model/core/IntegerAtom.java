package asl.model.core;

import org.jetbrains.annotations.Nullable;

/**
 * Могут неявно приводиться к типу ASLDouble
 */
public class IntegerAtom extends SyntaxAtom<Integer> implements Numeric {
    public IntegerAtom(int value) {
        super(value);
    }

    @Override
    public Number number() {
        return value;
    }

    @Override
    @Nullable
    public Numeric plus(Numeric other) {
        return other instanceof IntegerAtom
                ? new IntegerAtom(value + other.number().intValue())
                : new DoubleAtom(value + other.number().doubleValue());
    }

    @Override
    public Numeric minus(Numeric other) {
        return other instanceof IntegerAtom
                ? new IntegerAtom(value - other.number().intValue())
                : new DoubleAtom(value - other.number().doubleValue());
    }
}
