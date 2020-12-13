package asl.model.core;

import asl.model.SequenceFacade;
import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(
                "(true, 100)",
                SequenceFacade.createSequence(BooleanAtom.TRUE, new IntegerAtom(100)).toString()
        );
    }
}