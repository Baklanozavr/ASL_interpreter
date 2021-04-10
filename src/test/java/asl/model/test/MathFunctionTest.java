package asl.model.test;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract public class MathFunctionTest {
    protected static List<ASLObject> toArgsList(int... args) {
        return Arrays.stream(args).mapToObj(IntegerAtom::of).collect(Collectors.toList());
    }

    protected static List<ASLObject> toArgsList(double... args) {
        return Arrays.stream(args).mapToObj(DoubleAtom::of).collect(Collectors.toList());
    }
}
