package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция not имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u = true, то возвратить значение false.
 * Если u = false, то возвратить значение true.
 * Если u ∉ Boolean, то возвратить джамп типа notJump
 */
public class NotFunction extends DefinedFunction {
    public static final String name = "not";

    public NotFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        BooleanAtom argValue = evalArgAs(0, context, BooleanAtom.class);
        return argValue == TRUE ? FALSE : TRUE;
    }
}
