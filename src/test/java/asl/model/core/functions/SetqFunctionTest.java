package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.LocalVariable;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
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
        ASLObject result = new FunctionCall(Setq.name, List.of(x, y)).evaluate(funcOutput);
    }

    @Test
    public void setVariableTest() {
        ASLObject x = new LocalVariable("testName");
        IntegerAtom y = IntegerAtom.of(-10);

        Context funcOutput = new Context(null);
        ASLObject result = new FunctionCall(Setq.name, List.of(x, y)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", y, result);
        Assert.assertSame("Unexpected variable in context", y, funcOutput.getLocalVariable("testName"));
    }

    @Test
    public void setVariableToVariableTest() {
        // $y = -10; <-- in parent context
        // $x = $y; <-- tested
        ASLObject x = new LocalVariable("testX");
        ASLObject y = new LocalVariable("testY");
        IntegerAtom varValue = IntegerAtom.of(-10);

        Context funcOutput = new Context(null);
        funcOutput.putLocalVariable("testY", varValue);

        ASLObject result = new FunctionCall(Setq.name, List.of(x, y)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", varValue, result);
        Assert.assertSame("Unexpected variable in context", varValue, funcOutput.getLocalVariable("testX"));
        Assert.assertSame("Unexpected variable in context", varValue, funcOutput.getLocalVariable("testY"));
    }
}
