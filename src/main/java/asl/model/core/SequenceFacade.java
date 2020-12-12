package asl.model.core;

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

    public static Optional<IntegerAtom> getSequenceLength(@NotNull Thing thing) {
        Thing seqLength = thing.get(SEQ_LEN);
        if (seqLength instanceof IntegerAtom) {
            IntegerAtom length = (IntegerAtom) seqLength;
            return Optional.of(length).filter(len -> len.value() >= 0);
        }
        return Optional.empty();
    }

    public static boolean isSequence(@NotNull Thing thing) {
        return getSequenceLength(thing).isPresent();
    }

    public static boolean isNotSequence(@NotNull Thing thing) {
        return getSequenceLength(thing).isEmpty();
    }

    public static Thing getElementByIndex(@NotNull Thing thing, int index) {
        return thing.get(String.valueOf(index));
    }

    public static String sequenceToString(@NotNull Thing thing) {
        return getSequenceLength(thing)
                .map(SyntaxAtom::value)
                .map(length -> IntStream.range(0, length)
                        .mapToObj(IntegerAtom::new)
                        .map(thing::get)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "(", ")")))
                .orElse("");
    }

    @NotNull
    public static Attributon createSequence(Thing... elements) {
        Attributon result = new Attributon();
        int seqLen = elements.length;
        result.put(SEQ_LEN, new IntegerAtom(seqLen));
        for (int i = 0; i < seqLen; ++i) {
            result.put(new IntegerAtom(i), elements[i]);
        }
        return result;
    }

    private boolean listsAreEqual(List<Thing> left, List<Thing> right) {
        if (left.size() != right.size())
            return false;
        for (int i = 0; i < left.size(); ++i)
            if (!Objects.equals(left.get(i), right.get(i)))
                return false;
        return true;
    }
}
