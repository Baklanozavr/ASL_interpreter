package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция seqLength имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u – последовательность, то возвратить значение, которое является длиной последовательности u.
 * В противном случае, возвратить джамп типа seqLengthJump
 */
public class SeqLength extends SequenceFunction {
    public static final String name = "seqLength";

    public SeqLength(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return evalArgAsSequence(0, context).getSeqLen();
    }
}
