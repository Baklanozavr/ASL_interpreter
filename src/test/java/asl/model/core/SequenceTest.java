package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(
                "(BooleanAtom(true), undef, IntegerAtom(100))",
                new Sequence(BooleanAtom.TRUE, null, new IntegerAtom(100)).toString()
        );
    }
}