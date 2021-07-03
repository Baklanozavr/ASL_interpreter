package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.IntegerAtom;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArefFunctionTest {

    @Test
    public void oneVariableTest() {
        ASLObject x = IntegerAtom.of(-10);

        Context funcOutput = new Context(null);
        ASLObject result = new Aref(List.of(x)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", x, result);
    }

    @Test
    public void twoVariablesTest() {
        PlainAttributon x = new PlainAttributon();
        ASLObject y = QNameAtom.create("testKey");
        ASLObject expected = IntegerAtom.of(12);
        x.put(y, expected);

        Context funcOutput = new Context(null);
        ASLObject result = new Aref(List.of(x, y)).evaluate(funcOutput);
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

        Context funcOutput = new Context(null);

        ASLObject result = new Aref(List.of(x, y, z)).evaluate(funcOutput);
        Assert.assertSame("Unexpected result", expected, result);
    }
}
