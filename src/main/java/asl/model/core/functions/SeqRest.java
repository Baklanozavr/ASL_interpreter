package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

/**
 * Функция seqRest имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u – не последовательность, то возвратить джамп типа seqRestJump.
 * Если u – пустая последовательность, то возвратить джамп типа seqRestJump.
 * В противном случае, если u = {seqLen = n, start = i, i+0 = u1, …, i+n-1 = un, w},
 * то заменить содержимое (??? мутация, а не копирование ???)
 * u на {seqLen = n-1, start = j, j+0 = u2, …, j+n-2 = un, w} и возвратить u
 */
public class SeqRest extends SequenceFunctionEvaluator {
    public static final String name = "seqRest";

    public SeqRest(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Sequence sequence = evalArgAsSequence(0, context);
        if (sequence.getSeqLen().equals(IntegerAtom.zero()))
            throw new Jump(getJumpType(), "Empty sequence!");

        var aslSequence = sequence.getAslSequence();
        SequenceFacade.setSequenceLength(aslSequence, sequence.getSeqLen().value() - 1);
        IntegerAtom start = sequence.getStart();
        aslSequence.put(start, Undef.UNDEF);
        SequenceFacade.setSequenceStart(aslSequence, start.value() + 1);
        return aslSequence;
    }
}
