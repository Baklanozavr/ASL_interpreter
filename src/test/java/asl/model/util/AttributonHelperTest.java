package asl.model.util;

import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

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
        expectSerialized("{ key1: value, key2: ^1 }\n" +
                "where\n" +
                "#1 = { cycle: ^1 }", attributon);
    }

    @Test
    public void selfReferencedAttributonTest() {
        PlainAttributon selfRefAttributon = new PlainAttributon();
        selfRefAttributon.put("key", selfRefAttributon);
        expectSerialized("#1 = { key: ^1 }", selfRefAttributon);
    }

    @Test
    public void attributonWithRootCycleTest() {
        PlainAttributon cycle1 = new PlainAttributon();
        cycle1.put("cycle1", cycle1);
        PlainAttributon cycle2 = new PlainAttributon();
        cycle2.put("cycle2", cycle2);
        // an attribute order is needed to prevent this test being flaky
        PlainAttributon root = new PlainAttributon(new LinkedHashMap<>());
        root.put("key1", QNameAtom.create("value"));
        root.put("key4", cycle2);
        root.put("key3", root);
        root.put("key2", cycle1);
        expectSerialized("#1 = { key1: value, key2: ^2, key3: ^1, key4: ^3 }\n" +
                "where\n" +
                "#2 = { cycle1: ^2 }\n" +
                "#3 = { cycle2: ^3 }", root);
    }

    private void expectSerialized(String expected, ASLObjectWithAttributes attributon) {
        Assert.assertEquals("Unexpected result", expected, new AttributonHelper(attributon).getString());
    }
}
