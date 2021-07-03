package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция and имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Если n = 0, то возвратить значение true.
 * Если n > 0, то
 * Если u1, …, un ∈ {true}, то возвратить значение true.
 * Если u1, …, ui-1 ∈ {true}, и ui = false, то не вычислять xi+1, …, xn и возвратить значение false
 * Если u1, …, ui-1 ∈ {true}, и ui ∉ Boolean, то не вычислять xi+1, …, xn и возвратить джамп типа andJump
 */
public class And extends FunctionEvaluator {
    public static final String name = "and";

    public And(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        int argsNumber = f.arguments.size();
        for (int i = 0; i < argsNumber; ++i) {
            BooleanAtom argValue = evalArgAs(i, context, BooleanAtom.class);
            if (argValue == FALSE)
                return FALSE;
        }
        return TRUE;
    }
}
