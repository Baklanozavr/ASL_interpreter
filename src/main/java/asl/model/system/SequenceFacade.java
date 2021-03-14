package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.Atom;
import asl.model.core.IntegerAtom;
import asl.model.core.PlainAttributon;
import asl.model.core.QNameAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Атрибутоны специального вида, называемые последовательностями,
 * определяют конечные последовательности вещей и характеризуются атрибутами seqLen и start [docs 3.5]
 * <p>
 * Пусть av – компонента состояния s.
 * Атрибутон tg называется последовательностью в состоянии s, если av(tg, seqLen) ∈ Integer, и av(tg, seqLen) ≥ 0.
 * Атрибутон tg называется пустой последовательностью в состоянии s, если av(tg, seqLen) = 0.
 */
public final class SequenceFacade {
    private static final QNameAtom SEQ_LEN = QNameAtom.create("seqLen");

    private SequenceFacade() {
    }

    public static Optional<IntegerAtom> getSequenceLength(@NotNull ASLObject aslObject) {
        if (aslObject instanceof ASLObjectWithAttributes) {
            var aslObjectWithAttributes = (ASLObjectWithAttributes) aslObject;
            ASLObject seqLength = aslObjectWithAttributes.get(SEQ_LEN);
            if (seqLength instanceof IntegerAtom) {
                IntegerAtom length = (IntegerAtom) seqLength;
                return Optional.of(length).filter(len -> len.value() >= 0);
            }
        }
        return Optional.empty();
    }

    public static boolean isSequence(@NotNull ASLObject aslObject) {
        return getSequenceLength(aslObject).isPresent();
    }

    public static boolean isNotSequence(@NotNull ASLObject aslObject) {
        return getSequenceLength(aslObject).isEmpty();
    }

    public static String sequenceToString(@NotNull PlainAttributon plainAttributon) {
        return getSequenceLength(plainAttributon)
                .map(Atom::value)
                .map(length -> IntStream.range(1, length + 1)
                        .mapToObj(IntegerAtom::of)
                        .map(plainAttributon::get)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "(", ")")))
                .orElse("");
    }

    @NotNull
    public static PlainAttributon createSequence(ASLObject... elements) {
        PlainAttributon result = new PlainAttributon();
        result.put(SEQ_LEN, IntegerAtom.of(elements.length));
        for (int i = 0; i < elements.length; ++i) {
            result.put(i + 1, elements[i]);
        }
        return result;
    }

    @NotNull
    public static PlainAttributon appendToSequence(ASLObject seq, ASLObject... elements) {
        Optional<IntegerAtom> seqLength = getSequenceLength(seq);
        if (seqLength.isEmpty())
            throw new IllegalArgumentException("Not a sequence!");

        PlainAttributon sequence = (PlainAttributon) seq;
        int oldSeqLen = seqLength.get().value();
        int newSeqLength = oldSeqLen + elements.length;
        sequence.put(SEQ_LEN, IntegerAtom.of(newSeqLength));
        for (int i = 0; i < elements.length; ++i) {
            sequence.put(oldSeqLen + i + 1, elements[i]);
        }
        return sequence;
    }

    private boolean listsAreEqual(List<ASLObject> left, List<ASLObject> right) {
        if (left.size() != right.size())
            return false;
        for (int i = 0; i < left.size(); ++i)
            if (!Objects.equals(left.get(i), right.get(i)))
                return false;
        return true;
    }
}
