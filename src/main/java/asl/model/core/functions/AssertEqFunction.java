package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция assertEq имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x и y возвращают значения ux и uy.
 * Если ux = uy, то возвратить значение true.
 * Если ux ≠ uy, то возвратить джамп типа assertEqJump.
 */
public class AssertEqFunction extends DefinedFunction {
    public static final String name = "assertEq";

    public AssertEqFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject left = arguments.get(0).evaluate(context);
        ASLObject right = arguments.get(1).evaluate(context);
        if (left.equals(right))
            return BooleanAtom.TRUE;

        throw new Jump(getJumpType());
    }
}
