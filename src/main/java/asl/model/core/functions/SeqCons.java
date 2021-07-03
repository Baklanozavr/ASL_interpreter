package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import org.jetbrains.annotations.NotNull;

/**
 * Функция seqCons имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * Если x возвращает джамп u, то не вычислять y и возвратить джамп u.
 * Если y возвращает джамп u, то возвратить джамп u.
 * Если uy – не последовательность, то возвратить джамп типа sequenceJump.
 * Если uy = {seqLen = n, start = i, i+0 = u1, …, i+n-1 = un, w}, то заменить содержимое атрибутона uy
 * на {seqLen = n+1, start = i, i+0 = ux, i+1 = u1, …, i+n = un, w} и возвратить значение uy
 */
public class SeqCons extends SequenceFunctionEvaluator {
    public static final String name = "seqCons";

    public SeqCons(FunctionCall f) {
        super(f);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        var firstArgValue = f.arguments.get(0).evaluate(context);
        var sequence = evalArgAsSequence(1, context);
        var aslSequence = sequence.getAslSequence();
        SequenceFacade.setSequenceLength(aslSequence, sequence.getSeqLen().value() + 1);
        var newStart = IntegerAtom.of(sequence.getStart().value() - 1);
        SequenceFacade.setSequenceStart(aslSequence, newStart);
        return aslSequence.put(newStart, firstArgValue);
    }
}
