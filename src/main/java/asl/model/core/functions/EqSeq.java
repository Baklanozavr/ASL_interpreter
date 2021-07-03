package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция eqSeq имеет аргументы (x1, …, xn), где n > 0. Пусть свойство P(u1, u2) означает,
 * что либо u1 и u2 – последовательности длины 0, либо u1 и u2 – последовательности длины n > 0,
 * которые имеют те же самые элементы и в том же самом порядке. Тогда eqSeq определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Если 1<i≤ n, и P(u1, uj) для 1 < j < i, и P(u1, ui), то не вычислять xi+1, …, xn и возвратить значение false.
 * Если P(u1, ui) для 1 < i ≤ n, то возвратить значение true.
 */
public class EqSeq extends SequenceFunctionEvaluator {
    public static final String name = "eqSeq";

    public EqSeq(FunctionCall f) {
        super(f);
        assertArgumentsSizeMoreThan(1);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        var refSeq = evalArgAsSequence(0, context);
        for (ASLObject argument : f.arguments.subList(1, f.arguments.size())) {
            var argSeq = evalArgAsSequence(1, context);
            if (!refSeq.equals(argSeq)) {
                return BooleanAtom.FALSE;
            }
        }
        return BooleanAtom.TRUE;
    }
}
