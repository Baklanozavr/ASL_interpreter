package asl.model.core;

import org.junit.Assert;
import org.junit.Test;

public class SequenceFacadeTest {

    @Test
    public void createNull() {
        // если не сделать класс-каст, то будет NPE, так как null-массив
        Attributon seq = SequenceFacade.createSequence((Thing) null);
        Assert.assertNotNull(seq);

    }
}
