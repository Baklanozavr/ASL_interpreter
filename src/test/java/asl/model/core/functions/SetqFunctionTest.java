package asl.model.core.functions;

import asl.model.AttributonFactory;
import asl.model.core.Attributes;
import asl.model.core.Context;
import asl.model.core.IntegerAtom;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;
import org.junit.Assert;
import org.junit.Test;

import static asl.model.core.Attributes.*;

public class SetqFunctionTest extends FunctionTest {
    private static final QNameAtom functionName = Attributes.SETQ;

    @Test
    public void notVariableTest() {
        Thing x = new IntegerAtom(3);
        Thing y = new IntegerAtom(4);

        Context parent = new Context();
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x, y);
        Assert.assertTrue("Expected jump not found", result.jump().is(SETQ_JUMP));
        Assert.assertTrue("Unexpected value", result.value().undefined());
        Assert.assertTrue("Unexpected variable in parent context", parent.variables().isEmpty());
    }

    @Test
    public void setVariableTest() {
        Thing x = AttributonFactory.makeVariable(QNameAtom.create("testName"));
        IntegerAtom y = new IntegerAtom(-10);

        Context parent = new Context();
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x, y);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", y, result.value());
        Assert.assertSame("Unexpected variable in parent context", y, parent.variables().get(x));
    }

    @Test
    public void setVariableToVariableTest() {
        // $y = -10; <-- in parent context
        // $x = $y; <-- tested
        Thing x = AttributonFactory.makeVariable(QNameAtom.create("testX"));
        Thing y = AttributonFactory.makeVariable(QNameAtom.create("testY"));
        IntegerAtom varValue = new IntegerAtom(-10);

        Context parent = new Context();
        parent.variables().put(y, varValue);
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x, y);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", varValue, result.value());
    }
}
