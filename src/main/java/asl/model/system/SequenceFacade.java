package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.IntegerAtom;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import asl.model.core.Undef;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Атрибутоны специального вида, называемые последовательностями,
 * определяют конечные последовательности вещей и характеризуются атрибутами seqLen и start
 * (спецификаторы длины и начального индекса последовательности).
 * <p>
 * Атрибутон называется последовательностью, если
 * значение его аттрибута seqLen ∈ Integer и ≥ 0,
 * значение его аттрибута start ∈ Integer или неопределенно
 * Последовательность называется пустой, если значение аттрибута seqLen равно 0.
 * Элемент последовательности с индексом i ∈ Integer определяется как значение аттрибута результата add(start, i).
 */
public final class SequenceFacade {
    private static final QNameAtom SEQ_LEN = QNameAtom.create("seqLen");
    private static final QNameAtom START = QNameAtom.create("start");
    public static final IntegerAtom DEFAULT_START = IntegerAtom.of(1);

    private SequenceFacade() {
    }

    public static void setSequenceLength(@NotNull ASLObjectWithAttributes sequence, int newLength) {
        sequence.put(SEQ_LEN, IntegerAtom.of(newLength));
    }

    public static void setSequenceStart(@NotNull ASLObjectWithAttributes sequence, int newStart) {
        sequence.put(START, IntegerAtom.of(newStart));
    }

    public static void setSequenceStart(@NotNull ASLObjectWithAttributes sequence, IntegerAtom newStart) {
        sequence.put(START, newStart);
    }

    /**
     * Метод, оборачивающий ASL-последовательность.
     * Если переданный объект не является последовательностью, возвращает {@code null}
     */
    @Nullable
    public static Sequence toSequence(@NotNull ASLObject aslObject) {
        // cast to attributon
        if (!(aslObject instanceof ASLObjectWithAttributes)) return null;
        var aslObjectWithAttributes = (ASLObjectWithAttributes) aslObject;

        // get seqLen
        ASLObject seqLenCandidate = aslObjectWithAttributes.get(SEQ_LEN);
        if (!(seqLenCandidate instanceof IntegerAtom)) return null;
        IntegerAtom seqLen = (IntegerAtom) seqLenCandidate;
        if (seqLen.value() < 0) return null;

        // get start
        ASLObject startCandidate = aslObjectWithAttributes.get(START);
        IntegerAtom seqStart;
        if (startCandidate instanceof IntegerAtom) {
            seqStart = (IntegerAtom) startCandidate;
        } else if (startCandidate == Undef.UNDEF) {
            seqStart = DEFAULT_START;
        } else return null;

        // return sequence
        return new Sequence(aslObjectWithAttributes, seqLen, seqStart);
    }

    @NotNull
    public static PlainAttributon createSequence(@NotNull List<ASLObject> elements) {
        int size = elements.size();
        var result = new PlainAttributon(size + 2); // +2 because of attributes for length and start
        result.put(SEQ_LEN, IntegerAtom.of(size));
        result.put(START, DEFAULT_START);
        int i = DEFAULT_START.value();
        for (ASLObject element : elements) {
            result.put(i, element);
            ++i;
        }
        return result;
    }
}
