package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция cond имеет аргументы (x1, y1, …, xn, yn, opt z), где n>1. Аргументы x1, …. xn называются условиями.
 * Эта функция определяется следующим образом:
 * Пусть x1, …. xn возвращают значения u1, …, un.
 * Если u1, …, ui-1 ∈ {false}, ui = true, 1≤ i≤ n, то вычислить yi.
 * Если u1, …, ui-1 ∈ {false}, ui ∉ Boolean, и 1≤ i≤ n, то не вычислять xi+1, …, xn и возвратить джамп типа condJump.
 * Если u1, …, un ∈ {false}, и z присутствует, то вычислить z.
 * Если u1, …, un ∈ {false}, и z отсутствует, то возвратить текущее состояние.
 */
public class CondFunction extends DefinedFunction {
    public static final String name = "cond";

    public CondFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        int argsSize = arguments.size();
        int condSize = argsSize / 2 * 2;
        for (int i = 0; i < condSize; ++i) {
            var condition = evalArgAs(i, context, BooleanAtom.class);
            ++i;
            if (condition == BooleanAtom.TRUE)
                return arguments.get(i).evaluate(context);
        }
        return condSize != argsSize ? arguments.get(condSize) : context.value();
    }
}
