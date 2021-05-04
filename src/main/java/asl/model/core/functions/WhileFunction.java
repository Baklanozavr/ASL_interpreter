package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция while имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x возвращает значение ux.
 * Если ux = type, то вычислить u и перейти к шагу 1.
 * Если ux = false, то закончить вычисление.
 * Если ux ∉ Boolean, то возвратить джамп типа whileJump.
 */
public class WhileFunction extends DefinedFunction {
    public static final String name = "while";

    public WhileFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject result = Undef.UNDEF;
        while (evalArgAs(0, context, BooleanAtom.class) == BooleanAtom.TRUE) {
            result = arguments.get(1).evaluate(context);
        }
        return result;
    }
}
