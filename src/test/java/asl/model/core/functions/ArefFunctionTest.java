package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArefFunctionTest {
    private static ASLObject evalArefInEmptyContext(ASLObject... vars) {
        return new FunctionCall("aref", List.of(vars)).evaluate(Context.empty());
    }

    @Test
    public void oneVariableTest() {
        ASLObject x = IntegerAtom.of(-10);

        ASLObject result = evalArefInEmptyContext(x);
        Assert.assertSame("Unexpected result", x, result);
    }

    @Test
    public void twoVariablesTest() {
        PlainAttributon x = new PlainAttributon();
        ASLObject y = QNameAtom.create("testKey");
        ASLObject expected = IntegerAtom.of(12);
        x.put(y, expected);

        ASLObject result = evalArefInEmptyContext(x, y);
        Assert.assertSame("Unexpected result", expected, result);
    }

    @Test
    public void threeVariablesTest() {
        PlainAttributon x = new PlainAttributon();
        ASLObject y = Undef.UNDEF;
        ASLObject z = QNameAtom.create("testKey");
        PlainAttributon middle = new PlainAttributon();
        ASLObject expected = IntegerAtom.of(12);
        middle.put(z, expected);
        x.put(y, middle);

        ASLObject result = evalArefInEmptyContext(x, y, z);
        Assert.assertSame("Unexpected result", expected, result);
    }
}
