package asl.model.core.functions;

import asl.model.core.Attributes;
import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.IntegerAtom;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;
import asl.model.core.Undef;
import org.junit.Assert;
import org.junit.Test;

public class ArefFunctionTest extends FunctionTest {
    private static final QNameAtom functionName = Attributes.AREF;

    @Test
    public void oneVariableTest() {
        Thing x = new IntegerAtom(-10);

        Context parent = new Context();
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", x, result.value());
    }

    @Test
    public void twoVariablesTest() {
        Attributon x = new Attributon();
        Thing y = QNameAtom.create("testKey");
        Thing expected = new IntegerAtom(12);
        x.put(y, expected);

        Context parent = new Context();
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x, y);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", expected, result.value());
    }

    @Test
    public void threeVariablesTest() {
        Attributon x = new Attributon();
        Thing y = Undef.UNDEF;
        Thing z = QNameAtom.create("testKey");
        Attributon middle = new Attributon();
        Thing expected = new IntegerAtom(12);
        middle.put(z, expected);
        x.put(y, middle);

        Context parent = new Context();
        Context funcContext = new Context(parent);

        Context result = evalFunc(functionName, funcContext, x, y, z);
        Assert.assertTrue("Unexpected jump", result.jump().undefined());
        Assert.assertSame("Unexpected value", expected, result.value());
    }

}
