package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class BooleanAtomTest {

    @Test
    public void testValues() {
        Assert.assertSame(BooleanAtom.TRUE, QNameAtom.create("true"));
        Assert.assertSame(BooleanAtom.FALSE, QNameAtom.create("false"));
    }
}