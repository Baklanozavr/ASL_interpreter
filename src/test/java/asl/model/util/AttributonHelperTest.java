package asl.model.util;

import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import org.junit.Assert;
import org.junit.Test;

public class AttributonHelperTest {

    @Test
    public void noAttributesTest() {
        expectSerialized("{}", new PlainAttributon());
    }

    @Test
    public void plainAttributonTest() {
        PlainAttributon plainAttributon = new PlainAttributon();
        plainAttributon.put("key", QNameAtom.create("value"));
        expectSerialized("{ key: value }", plainAttributon);
    }

    @Test
    public void attributonWithCycleTest() {
        PlainAttributon cycle = new PlainAttributon();
        cycle.put("cycle", cycle);
        PlainAttributon attributon = new PlainAttributon();
        attributon.put("key1", QNameAtom.create("value"));
        attributon.put("key2", cycle);
        expectSerialized("#1 = { cycle: #1 };\n" +
                "{ key1: value, key2: #1 }", attributon);
    }

    @Test
    public void selfReferencedAttributonTest() {
        PlainAttributon selfRefAttributon = new PlainAttributon();
        selfRefAttributon.put("key", selfRefAttributon);
        expectSerialized("#1 = { key: #1 }", selfRefAttributon);
    }

    private void expectSerialized(String expected, ASLObjectWithAttributes attributon) {
        Assert.assertEquals("Unexpected result", expected, new AttributonHelper(attributon).getString());
    }
}
