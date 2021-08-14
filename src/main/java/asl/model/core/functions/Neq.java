package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция neq имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * Если ux ≠ uy, то возвратить значение true.
 * Если ux = uy, то возвратить значение false.
 */
public class Neq extends FunctionEvaluator {
    public static final String name = "neq";

    public Neq(FunctionCall f) {
        super(f);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject left = f.arguments.get(0).evaluate(context);
        ASLObject right = f.arguments.get(1).evaluate(context);
        return left.equalsLink(right) ? FALSE : TRUE;
    }
}
