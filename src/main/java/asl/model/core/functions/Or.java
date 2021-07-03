package asl.model.core.functions;

import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция or имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Если n = 0, то возвратить значение false.
 * Если n > 0, то
 * Если u1, …, un ∈ {false}, то возвратить значение false.
 * Если u1, …, ui-1 ∈ {false}, и ui = true, то не вычислять xi+1, …, xn и возвратить значение true.
 * Если u1, …, ui-1 ∈ {false}, и ui ∉ Boolean, то не вычислять xi+1, …, xn и возвратить джамп типа orJump
 */
public class Or extends FunctionEvaluator {
    public static final String name = "or";

    public Or(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        int argsNumber = f.arguments.size();
        for (int i = 0; i < argsNumber; ++i) {
            BooleanAtom argValue = evalArgAs(i, context, BooleanAtom.class);
            if (argValue == TRUE)
                return TRUE;
        }
        return FALSE;
    }
}
