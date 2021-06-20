package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция assert имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u = true, то возвратить текущее состояние.
 * Если u ∉ {true}, то возвратить джамп типа assertJump
 */
public class AssertFunction extends DefinedFunction {
    public static final String name = "assert";

    public AssertFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = arguments.get(0).evaluate(context);
        if (argValue != BooleanAtom.TRUE)
            throw new Jump(getJumpType());

        return context.value();
    }
}
