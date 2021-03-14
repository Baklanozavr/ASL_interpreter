package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.Variable;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SetqFunctionTest {

    @Test(expected = Jump.class)
    public void notVariableTest() {
        ASLObject x = IntegerAtom.of(3);
        ASLObject y = IntegerAtom.of(4);

        Context funcOutput = new Context(null);
        ASLObject result = new SetqFunction(List.of(x, y)).evaluate(funcOutput);
    }

    @Test
    public void setVariableTest() {
        ASLObject x = new Variable("testName");
        IntegerAtom y = IntegerAtom.of(-10);

        Context funcOutput = new Context(null);
        ASLObject result = new SetqFunction(List.of(x, y)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", y, result);
        Assert.assertSame("Unexpected variable in context", y, funcOutput.getVariable("testName"));
    }

    @Test
    public void setVariableToVariableTest() {
        // $y = -10; <-- in parent context
        // $x = $y; <-- tested
        ASLObject x = new Variable("testX");
        ASLObject y = new Variable("testY");
        IntegerAtom varValue = IntegerAtom.of(-10);

        Context funcOutput = new Context(null);
        funcOutput.putVariable("testY", varValue);

        ASLObject result = new SetqFunction(List.of(x, y)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", varValue, result);
        Assert.assertSame("Unexpected variable in context", varValue, funcOutput.getVariable("testX"));
        Assert.assertSame("Unexpected variable in context", varValue, funcOutput.getVariable("testY"));
    }
}
