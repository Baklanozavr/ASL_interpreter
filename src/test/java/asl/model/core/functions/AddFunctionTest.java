package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.test.MathFunctionTest;
import asl.model.test.TestArgument;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AddFunctionTest extends MathFunctionTest {

    private static NumericAtom<?> evaluateAdd(List<ASLObject> arguments) {
        return (NumericAtom<?>) new FunctionCall(Add.name, arguments).evaluate(Context.empty());
    }

    private static int add(int... args) {
        return (int) evaluateAdd(toArgsList(args)).value();
    }

    private static double add(double... args) {
        return (double) evaluateAdd(toArgsList(args)).value();
    }

    @Test
    public void zeroTest() {
        assertEquals(0, add(0));
        assertEquals(0, add(0.0), 0.0000001);
    }

    @Test
    public void severalArgsTest() {
        assertEquals(12, add(5, 4, 3));
        assertEquals(1.2, add(0.5, 0.4, 0.3), 0.0000001);
    }

    @Test
    public void differentArgsTest() {
        var x1 = DoubleAtom.of(0);
        var x2 = IntegerAtom.of(1);
        NumericAtom<?> result = evaluateAdd(List.of(x1, x2));
        assertTrue(result instanceof DoubleAtom);
        assertEquals(1.0, ((DoubleAtom) result).value(), 0.0000001);
    }

    @Test(expected = Jump.class)
    public void noArgsTest() {
        assertNotNull(evaluateAdd(emptyList()));
    }

    @Test
    public void evaluationOrderTest() {
        var x1 = new TestArgument<>(IntegerAtom.zero());
        var x2 = new TestArgument<>(Undef.UNDEF);
        var x3 = new TestArgument<>(IntegerAtom.zero());
        try {
            evaluateAdd(List.of(x1, x2, x3));
        } catch (Jump ignore) {
        }
        assertTrue(x1.isEvaluated());
        assertTrue(x2.isEvaluated());
        assertFalse(x3.isEvaluated());
    }
}
