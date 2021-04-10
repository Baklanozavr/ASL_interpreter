package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isInteger;

/**
 * Функция div имеет аргументы (x, y) и определяется следующим образом:
 * <li> Пусть x, y возвращают значения ux, uy. </li>
 * <li> Если ux ∉ Integer ∪ Double, то не вычислять y и возвратить джамп типа Div.
 * <li> Если ux ∈ Integer ∪ Double, то
 * <li> Если uy ∉ Integer ∪ Double, то возвратить джамп типа divJump.
 * <li> Если uy ∈ Integer ∪ Double, то
 * <li> Если uy ≠ 0, то
 * <li> Если ux, uy ∈ Integer, то возвратить значение, которое является результатом целочисленного деления ux на uy.
 * <li> Если ux ∉ Integer, или uy ∉ Integer, то возвратить значение, которое является частным от деления ux на uy.
 * <li> Если uy = 0, то возвратить джамп типа divJump.
 */
public class DivFunction extends MathFunction {
    public DivFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.DIV, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        var x = evalNumericArgument(0, context);
        var y = evalNumericArgument(1, context);
        if (getInt(y) == 0)
            throw new Jump(getJumpType());

        return isInteger(x) && isInteger(y) ?
                IntegerAtom.of(getInt(x) / getInt(y)) :
                DoubleAtom.of(getDouble(x) / getDouble(y));
    }
}
