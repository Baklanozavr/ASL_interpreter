package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция seqPrepend имеет аргументы (x1, …, xn, x) и определяется следующим образом:
 * Пусть x1, …, xn, x возвращают значения u1, …, un, u.
 * Если w1 – первый элемент последовательности (u1, …, un, u), и w1 возвращает джамп  w2,
 * то не вычислять остальные элементы этой последовательности и возвратить джамп w2.
 * Если u – не последовательность, то возвратить джамп типа seqPrependJump.
 * В противном случае, если u = {seqLen = m, start = i, i+0 = v1, …, i+m-1 = vm, w}, где w – остальные пары атрибутов,
 * то заменить содержимое u на {seqLen = m+n, start = i-n, i-n = u1, …, i-1 = un, i+0 = v1, …, i+m-1 = vm, w}
 * и возвратить значение u
 */
public class SeqPrepend extends SequenceFunction {
    public static final String name = "seqPrepend";

    public SeqPrepend(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        List<ASLObject> evaluatedArguments = evaluateArguments(context);
        int newElementsNumber = evaluatedArguments.size() - 1;
        var lastArgument = evaluatedArguments.get(newElementsNumber);
        Sequence sequence = castToSequence(lastArgument, newElementsNumber);
        var aslSequence = sequence.getAslSequence();
        SequenceFacade.setSequenceLength(aslSequence, sequence.getSeqLen().value() + newElementsNumber);
        int i = sequence.getStart().value() - newElementsNumber;
        SequenceFacade.setSequenceStart(aslSequence, i);
        for (ASLObject element : evaluatedArguments.subList(0, newElementsNumber)) {
            aslSequence.put(i, element);
            ++i;
        }
        return aslSequence;
    }
}
