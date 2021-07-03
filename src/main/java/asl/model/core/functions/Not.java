package asl.model.core.functions;

import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция not имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u = true, то возвратить значение false.
 * Если u = false, то возвратить значение true.
 * Если u ∉ Boolean, то возвратить джамп типа notJump
 */
public class Not extends FunctionEvaluator {
    public static final String name = "not";

    public Not(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        BooleanAtom argValue = evalArgAs(0, context, BooleanAtom.class);
        return argValue == TRUE ? FALSE : TRUE;
    }
}
