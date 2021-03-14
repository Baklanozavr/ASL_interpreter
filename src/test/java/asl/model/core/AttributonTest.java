package asl.model.core;

import asl.ASLTest;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

public class AttributonTest implements ASLTest {

    @Test
    public void testVariableEval() {
        ASLObject value = IntegerAtom.of(13);

        Context output = new Context(null);
        output.putVariable("testName", value);
        ASLObject result = new Variable("testName").evaluate(output);

        Assert.assertSame("Unexpected result", value, result);
        Assert.assertSame("No variable in context", value, output.getVariable("testName"));
    }
}