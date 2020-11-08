package asl.model.core;

/**
 * Могут неявно приводиться к типу ASLDouble
 */
public class ASLInteger extends SyntaxAtom<Integer> implements Numeric {
    public static final ASLInteger ZERO = new ASLInteger(0);

    private Integer value;

    ASLInteger(int value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
