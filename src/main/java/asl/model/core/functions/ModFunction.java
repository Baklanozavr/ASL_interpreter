package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция mod имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * <li> Если ux ∉ Integer, то не вычислять y и возвратить джамп типа modJump.
 * <li> Если ux ∈ Integer, то
 * <li> Если uy ∉ Integer, то возвратить джамп типа modJump.
 * <li> Если uy ∈ Integer, то
 * <li> Если uy ≠ 0, то возвратить значение, которое является остатком от деления ux на uy.
 * <li> Если uy = 0, то возвратить джамп типа modJump.
 */
public class ModFunction extends MathFunction {
    public static final String name = "mod";

    public ModFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        int x = getIntArgument(0, context);
        int y = getIntArgument(1, context);
        if (y == 0)
            throw new Jump(getJumpType());

        return IntegerAtom.of(x % y);
    }

    private int getIntArgument(int i, Context context) {
        return evalArgAs(i, context, IntegerAtom.class).value();
    }
}
