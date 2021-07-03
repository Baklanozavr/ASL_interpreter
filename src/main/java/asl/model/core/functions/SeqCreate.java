package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import org.jetbrains.annotations.NotNull;

/**
 * Функция seqCreate имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Породить новую последовательность w из элементов u1, …, un и возвратить значение w.
 */
public class SeqCreate extends FunctionEvaluator {
    public static final String name = "seqCreate";

    public SeqCreate(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return SequenceFacade.createSequence(evaluateArguments(context));
    }
}
