package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.NumericAtom;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddFunctionTest {

    private static int calculate(int... args) {
        List<ASLObject> arguments = Arrays.stream(args).mapToObj(IntegerAtom::of).collect(Collectors.toList());
        NumericAtom<?> numeric = new AddFunction(arguments).evaluate(new Context(null));
        return (int) numeric.value();
    }

    private static double calculate(double... args) {
        List<ASLObject> arguments = Arrays.stream(args).mapToObj(DoubleAtom::of).collect(Collectors.toList());
        NumericAtom<?> numeric = new AddFunction(arguments).evaluate(new Context(null));
        return (double) numeric.value();
    }

    @Test
    public void zeroTest() {
        Assert.assertEquals(0, calculate(0));
        Assert.assertEquals(0, calculate(0.0), 0.0000001);
    }
}