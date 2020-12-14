package asl.model.core;

import asl.ASLTest;
import asl.model.AttributonFactory;
import org.junit.Assert;
import org.junit.Test;

public class AttributonTest implements ASLTest {

    @Test
    public void testVariableEval() {
        Thing variable = AttributonFactory.makeVariable(QNameAtom.create("testName"));
        Thing value = new IntegerAtom(13);

        Context context = new Context();
        context.variables().put(variable, value);

        Context result = variable.eval(context, GC);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", value, result.value());
    }
}