package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DivFunctionTest {

    private static DivFunction div(int x, int y) {
        return new DivFunction(List.of(IntegerAtom.of(x), IntegerAtom.of(y)));
    }

    private static DivFunction div(int x, double y) {
        return new DivFunction(List.of(IntegerAtom.of(x), DoubleAtom.of(y)));
    }

    private static DivFunction div(double x, int y) {
        return new DivFunction(List.of(DoubleAtom.of(x), IntegerAtom.of(y)));
    }

    private static DivFunction div(double x, double y) {
        return new DivFunction(List.of(DoubleAtom.of(x), DoubleAtom.of(y)));
    }

    @Test(expected = Jump.class)
    public void zeroDivisionOnlyIntTest() {
        div(1, 0).evaluate(new Context(null));
    }

    @Test(expected = Jump.class)
    public void zeroDivisionZeroIntTest() {
        div(1.0, 0).evaluate(new Context(null));
    }

    @Test(expected = Jump.class)
    public void zeroDivisionZeroDoubleTest() {
        div(1, 0.0).evaluate(new Context(null));
    }

    @Test(expected = Jump.class)
    public void zeroDivisionOnlyDoubleTest() {
        div(1.0, 0.0).evaluate(new Context(null));
    }

    @Test
    public void intDivisionTest() {
        ASLObject result = div(10, 7).evaluate(new Context(null));
        Assert.assertEquals(3, ((NumericAtom<?>) result).value());
    }

    @Test
    public void oneIntDividerTest() {
        ASLObject result = div(10, 2.5).evaluate(new Context(null));
        Assert.assertEquals(4.0, ((NumericAtom<?>) result).value());
    }

    @Test
    public void oneIntDivTest() {
        ASLObject result = div(12.5, 5).evaluate(new Context(null));
        Assert.assertEquals(2.5, ((NumericAtom<?>) result).value());
    }

    @Test
    public void doubleDivTest() {
        ASLObject result = div(12.5, 2.5).evaluate(new Context(null));
        Assert.assertEquals(5.0, ((NumericAtom<?>) result).value());
    }
}
