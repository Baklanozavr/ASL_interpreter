package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

/**
 * Возвращает индекс элемента в последовательности (сравнение происходит по ссылке)
 * (нужно помнить, что начало последовательности может быть не с 0 или 1)
 * Если элемента в последовательности нет, возвращает {@link asl.model.core.Undef}
 */
public class SeqFindIndexByValue extends SequenceFunctionEvaluator {
    public static final String name = "seqFindIndexByValue";

    public SeqFindIndexByValue(FunctionCall f) {
        super(f);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Sequence sequence = evalArgAsSequence(0, context);
        ASLObject objectToFind = f.arguments.get(1).evaluate(context);
        var aslSequence = sequence.getAslSequence();
        int start = sequence.getStart().value();
        int length = sequence.getSeqLen().value();
        for (int currentIndex = start; currentIndex < length; ++currentIndex) {
            ASLObject objectFromSeq = aslSequence.get(currentIndex);
            if (objectFromSeq.equalsLink(objectToFind))
                return IntegerAtom.of(currentIndex);
        }
        return Undef.UNDEF;
    }
}
