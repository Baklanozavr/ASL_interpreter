package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция neq имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * Если ux ≠ uy, то возвратить значение true.
 * Если ux = uy, то возвратить значение false.
 */
public class NeqFunction extends DefinedFunction {
    public static final String name = "neq";

    public NeqFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject left = arguments.get(0).evaluate(context);
        ASLObject right = arguments.get(1).evaluate(context);
        return left.equals(right) ? BooleanAtom.FALSE : BooleanAtom.TRUE;
    }
}
