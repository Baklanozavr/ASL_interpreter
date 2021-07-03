package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция seqAppend имеет аргументы (x, x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn, x возвращают значения u1, …, un, u.
 * Если w1 – первый элемент последовательности (u1, …, un, u), и w1 возвращает джамп  w2,
 * то не вычислять остальные элементы этой последовательности и возвратить джамп w2.
 * Если u – не последовательность, то возвратить джамп типа seqAppendJump.
 * В противном случае, если u = {seqLen = m, start = i, i+0 = v1, …, i+m-1 = vm, w}, где w – остальные пары атрибутов,
 * то заменить содержимое u на {seqLen = m+n, start = i, i+0 = v1, …, i+m-1 = vm,   i+m = u1, …, i+m+n-1 = un, w}
 * и возвратить значение u.
 */
public class SeqAppend extends SequenceFunctionEvaluator {
    public static final String name = "seqAppend";

    public SeqAppend(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        List<ASLObject> evaluatedArguments = evaluateArguments(context);
        int newElementsNumber = evaluatedArguments.size() - 1;
        var lastArgument = evaluatedArguments.get(newElementsNumber);
        Sequence sequence = castToSequence(lastArgument, newElementsNumber);
        var aslSequence = sequence.getAslSequence();
        var seqLength = sequence.getSeqLen().value();
        SequenceFacade.setSequenceLength(aslSequence, seqLength + newElementsNumber);
        int i = seqLength;
        for (ASLObject element : evaluatedArguments.subList(0, newElementsNumber)) {
            aslSequence.put(i, element);
            ++i;
        }
        return aslSequence;
    }
}
