package asl.model.core;

import asl.model.system.SequenceFacade;
import org.junit.Assert;
import org.junit.Test;

public class SequenceTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(
                "(true, 100)",
                SequenceFacade.createSequence(BooleanAtom.TRUE, IntegerAtom.of(100)).toString()
        );
    }
}