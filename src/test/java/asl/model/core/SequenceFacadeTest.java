package asl.model.core;

import asl.model.SequenceFacade;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class SequenceFacadeTest {

    @Test
    public void isSequenceTest() {
        Attributon seq = new Attributon();
        Assert.assertFalse("isSequence isn't detect not-a-sequence", SequenceFacade.isSequence(seq));
        Assert.assertTrue("isNotSequence isn't detect not-a-sequence", SequenceFacade.isNotSequence(seq));
        seq.put("seqLen", new IntegerAtom(2));
        Assert.assertTrue("isSequence isn't detect sequence", SequenceFacade.isSequence(seq));
        Assert.assertFalse("isNotSequence isn't detect sequence", SequenceFacade.isNotSequence(seq));
    }

    @Test
    public void createEmptyTest() {
        Attributon sequence = SequenceFacade.createSequence();
        Optional<IntegerAtom> boxedSequenceLength = SequenceFacade.getSequenceLength(sequence);
        Assert.assertTrue("no length!", boxedSequenceLength.isPresent());
        Assert.assertEquals("unexpected sequence length", 0, (int) boxedSequenceLength.get().value());
    }

    @Test
    public void createSeqTest() {
        Thing first = QNameAtom.create("test");
        Thing second = new IntegerAtom(5);
        Attributon sequence = SequenceFacade.createSequence(first, second);
        Optional<IntegerAtom> boxedSequenceLength = SequenceFacade.getSequenceLength(sequence);
        Assert.assertTrue("no length!", boxedSequenceLength.isPresent());
        Assert.assertEquals("unexpected sequence length", 2, (int) boxedSequenceLength.get().value());
        Assert.assertEquals("unexpected first element", first, sequence.get(1));
        Assert.assertEquals("unexpected second element", second, sequence.get(2));
    }

    @Test
    public void appendElementTest() {
        Thing first = QNameAtom.create("test");
        Thing second = new IntegerAtom(5);
        Attributon sequence = SequenceFacade.createSequence(first, second);

        Thing newElement = BooleanAtom.TRUE;
        SequenceFacade.appendToSequence(sequence, newElement);

        Optional<IntegerAtom> boxedSequenceLength = SequenceFacade.getSequenceLength(sequence);
        Assert.assertTrue("no length!", boxedSequenceLength.isPresent());
        Assert.assertEquals("unexpected sequence length", 3, (int) boxedSequenceLength.get().value());
        Assert.assertEquals("unexpected first element", first, sequence.get(1));
        Assert.assertEquals("unexpected second element", second, sequence.get(2));
        Assert.assertEquals("unexpected element", newElement, sequence.get(3));
    }
}
