package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция assertEq имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x и y возвращают значения ux и uy.
 * Если ux = uy, то возвратить значение true.
 * Если ux ≠ uy, то возвратить джамп типа assertEqJump.
 */
public class AssertEq extends FunctionEvaluator {
    public static final String name = "assertEq";

    public AssertEq(FunctionCall f) {
        super(f);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject left = f.arguments.get(0).evaluate(context);
        ASLObject right = f.arguments.get(1).evaluate(context);
        if (left.equals(right))
            return BooleanAtom.TRUE;

        throw new Jump(getJumpType());
    }
}
