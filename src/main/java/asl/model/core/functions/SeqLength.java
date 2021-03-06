package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция seqLength имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u – последовательность, то возвратить значение, которое является длиной последовательности u.
 * В противном случае, возвратить джамп типа seqLengthJump
 */
public class SeqLength extends SequenceFunctionEvaluator {
    public static final String name = "seqLength";

    public SeqLength(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return evalArgAsSequence(0, context).getSeqLen();
    }
}
