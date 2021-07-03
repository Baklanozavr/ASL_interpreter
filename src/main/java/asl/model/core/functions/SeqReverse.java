package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

/**
 * Функция seqReverse имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если x возвращает джамп w, то возвратить джамп w.
 * Если u – не последовательность, то возвратить джамп типа seqReverseJump.
 * В противном случае, если u = {seqLen= n, start = i, i+0 = u1, …, i+n-1 = un, w},
 * то заменить содержимое u на {seqLen= n, start = i, i+0 = un, …, i+n-1 = u1, w} и возвратить значение u.
 */
public class SeqReverse extends SequenceFunctionEvaluator {
    public static final String name = "seqReverse";

    public SeqReverse(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Sequence sequence = evalArgAsSequence(0, context);

        var aslSequence = sequence.getAslSequence();
        int leftIndex = sequence.getStart().value();
        int rightIndex = sequence.getSeqLen().value() + leftIndex - 1;
        while (leftIndex < rightIndex) {
            ASLObject oldLeft = aslSequence.get(leftIndex);
            aslSequence.put(leftIndex, aslSequence.get(rightIndex));
            aslSequence.put(rightIndex, oldLeft);
            ++leftIndex;
            --rightIndex;
        }
        return aslSequence;
    }
}
