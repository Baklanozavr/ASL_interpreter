package asl.model.core;

/**
 * Могут неявно приводиться к типу ASLDouble
 */
public class IntegerAtom extends SyntaxAtom<Integer> implements Numeric {
    public IntegerAtom(int value) {
        super(value);
    }
}
