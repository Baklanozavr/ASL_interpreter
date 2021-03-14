package asl.model.core;

import java.util.stream.IntStream;

public final class IntegerAtom extends NumericAtom<Integer> {
    private static final int CASH_SIZE = 256;
    private static final IntegerAtom[] CASH =
            IntStream.range(0, CASH_SIZE).mapToObj(IntegerAtom::new).toArray(IntegerAtom[]::new);

    public static IntegerAtom of(int value) {
        return value >= 0 && value < CASH_SIZE ?
                CASH[value] :
                new IntegerAtom(value);
    }

    public static IntegerAtom zero() {
        return CASH[0];
    }

    private IntegerAtom(int value) {
        super(value);
    }
}
