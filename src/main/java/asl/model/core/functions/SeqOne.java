package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Функция seqOne имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Если x1, …, xi-1 возвращают значения, и xi возвращает джамп w, то возвратить джамп w.
 * В противном случае, вычислить conz(seqLen, n, seqStart, 1, 1, quote(u1), …, n, quote(un)).
 */
public class SeqOne extends SequenceFunctionEvaluator {
    public static final String name = "seqOne";

    public SeqOne(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        List<ASLObject> elements = f.arguments.stream()
                .map(arg -> arg.evaluate(context))
                .map(List::of)
                .map(args -> new FunctionCall(Quote.name, args))
                .collect(Collectors.toList());
        return SequenceFacade.createSequence(elements);
    }
}
