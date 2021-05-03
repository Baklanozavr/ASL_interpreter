package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция seqFirst имеет аргументы (x) и определяется следующим образом:
 * Если x – не последовательность, то возвратить джамп типа seqFirstJump.
 * В противном случае,
 * Если aref(x, start) возвращает значение u ∉ Integer ∪ Undef, то возвратить джамп типа seqFirstJump.
 * Если aref(x, start) возвращает undef, то вычислить aref(x, 1).
 * В противном случае, вычислить aref(x, aref(x, start))
 */
public class SeqFirst extends SequenceFunction {
    public static final String name = "seqFirst";

    public SeqFirst(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Sequence sequence = evalArgAsSequence(0, context);
        var aslSequence = sequence.getAslSequence();
        var start = sequence.getStart();
        return aslSequence.get(start);
    }
}
