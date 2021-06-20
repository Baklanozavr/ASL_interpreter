package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция eq имеет аргументы (x1, …, xn), где n > 0, и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Если 1<i≤ n, и u1, …, ui-1 попарно равны, и ui ≠ ui-1, то не вычислять xi+1, …, xn и возвратить значение false.
 * Если u1, …, un попарно равны, то возвратить значение true.
 */
public class EqFunction extends DefinedFunction {
    public static final String name = "eq";

    public EqFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject first = arguments.get(0).evaluate(context);
        for (int i = 1; i < arguments.size(); ++i) {
            ASLObject next = arguments.get(i).evaluate(context);
            if (!first.equals(next))
                return BooleanAtom.FALSE;
        }
        return BooleanAtom.TRUE;
    }
}
