package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class ASLBooleanTest {

    @Test
    public void testValues() {
        Assert.assertSame(ASLBoolean.TRUE, ASLQName.create("true"));
        Assert.assertSame(ASLBoolean.FALSE, ASLQName.create("false"));
    }
}