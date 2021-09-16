package asl.model.core;

import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

public class AttributonTest {

    @Test
    public void testVariableEval() {
        ASLObject value = IntegerAtom.of(13);

        Context output = new Context(null);
        output.putLocalVariable("testName", value);
        ASLObject result = new LocalVariable("testName").evaluate(output);

        Assert.assertSame("Unexpected result", value, result);
        Assert.assertSame("No variable in context", value, output.getLocalVariable("testName"));
    }
}
