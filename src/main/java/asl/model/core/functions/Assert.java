package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция assert имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u = true, то возвратить текущее состояние.
 * Если u ∉ {true}, то возвратить джамп типа assertJump
 */
public class Assert extends FunctionEvaluator {
    public static final String name = "assert";

    public Assert(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = f.arguments.get(0).evaluate(context);
        if (argValue != BooleanAtom.TRUE)
            throw new Jump(getJumpType());

        return context.value();
    }
}
