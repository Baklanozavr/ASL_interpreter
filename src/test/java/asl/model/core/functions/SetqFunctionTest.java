package asl.model.core.functions;

import asl.model.AttributonFactory;
import asl.model.core.Context;
import asl.model.core.IntegerAtom;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;
import org.junit.Assert;
import org.junit.Test;

import static asl.model.core.Attributes.SETQ_JUMP;

public class SetqFunctionTest extends FunctionTest {

    @Test
    public void notVariableTest() {
        Context parent = new Context();
        Context funcContext = new Context(parent);
        setArguments(funcContext, new IntegerAtom(3), new IntegerAtom(4));

        Context result = SetqFunction.INSTANCE.eval(funcContext, GC);
        Assert.assertTrue("Expected jump not found", result.jump().is(SETQ_JUMP));
        Assert.assertTrue("Unexpected value", result.value().undefined());
        Assert.assertTrue("Unexpected variable in parent context", parent.variables().isEmpty());
    }

    @Test
    public void setVariableTest() {
        Thing x = AttributonFactory.makeVariable(QNameAtom.create("test name"));
        IntegerAtom y = new IntegerAtom(-10);

        Context parent = new Context();
        Context funcContext = new Context(parent);
        setArguments(funcContext, x, y);

        Context result = SetqFunction.INSTANCE.eval(funcContext, GC);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", y, result.value());
        Assert.assertSame("Unexpected variable in parent context", y, parent.variables().get(x));
    }
}
