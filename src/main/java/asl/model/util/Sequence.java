package asl.model.util;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.IntegerAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Обёртка над ASL-последовательностью.
 * Предполагает, что оборачиваемый ASL-объект не изменяется извне.
 */
public class Sequence {
    private final ASLObjectWithAttributes aslSequence;
    private final IntegerAtom seqLen;
    private final IntegerAtom start;

    /**
     * @param aslSequence - валидная asl-последовательность
     * @param seqLen      - длина данной последовательности
     * @param start       - начало данной последовательности
     */
    public Sequence(@NotNull ASLObjectWithAttributes aslSequence,
                    @NotNull IntegerAtom seqLen,
                    @NotNull IntegerAtom start) {
        this.aslSequence = aslSequence;
        this.seqLen = seqLen;
        this.start = start;
    }

    public ASLObjectWithAttributes getAslSequence() {
        return aslSequence;
    }

    public IntegerAtom getSeqLen() {
        return seqLen;
    }

    public IntegerAtom getStart() {
        return start;
    }

    public List<ASLObject> toList() {
        return toElementsStream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return toElementsStream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "(", ")"));
    }

    @Override
    public int hashCode() {
        return toElementsStream().collect(STREAM_HASHING_COLLECTOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var oSequence = (Sequence) o;
        int length = seqLen.value();
        if (oSequence.seqLen.value() != length) return false;
        int firstIndex = start.value();
        for (int i = firstIndex; i < length + firstIndex; ++i) {
            if (!aslSequence.get(i).equalsLink(oSequence.aslSequence.get(i))) {
                return false;
            }
        }
        return true;
    }

    private Stream<ASLObject> toElementsStream() {
        int startIndex = start.value();
        return IntStream.range(startIndex, seqLen.value() + startIndex)
                .mapToObj(aslSequence::get);
    }


    // https://stackoverflow.com/questions/39385860/how-to-compute-the-hash-code-for-a-stream-in-the-same-way-as-list-hashcode
    private static final int HASHING_CONSTANT = 31;
    private static final Collector<ASLObject, ?, Integer> STREAM_HASHING_COLLECTOR = Collector.of(
            () -> new int[2], // supplier
            (a, o) -> { // accumulator
                a[0] = a[0] * HASHING_CONSTANT + Objects.hashCode(o);
                a[1]++;
            },
            (a1, a2) -> { // combiner
                a1[0] = a1[0] * iPow(a2[1]) + a2[0];
                a1[1] += a2[1];
                return a1;
            },
            a -> iPow(a[1]) + a[0] // finisher
    );

    private static int iPow(int exp) {
        int base = HASHING_CONSTANT;
        int result = 1;
        for (; exp > 0; exp >>= 1, base *= base)
            if ((exp & 1) != 0) result *= base;
        return result;
    }
}
