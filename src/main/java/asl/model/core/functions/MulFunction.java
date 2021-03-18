package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.CommonAttributes;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.MUL_JUMP;
import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isInteger;
import static asl.model.util.MathUtils.isNotNumeric;
import static asl.model.util.MathUtils.isNumeric;

/**
 * Функция mul имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если n = 0, то возвратить значение 1.
 * <li> Если n > 0, то
 * <li> Если u1, …, un ∈ Integer ∪ Double, то возвратить значение, которое является произведением чисел u1, …, un.
 * <li> Если u1, …, ui-1 ∈ Integer ∪ Double, и ui ∉ Integer ∪ Double,
 * то не вычислять xi+1, …, xn и возвратить джамп типа mulJump.
 */
public class MulFunction extends MathFunction {
    public MulFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.MUL, arguments);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        NumericAtom<?> result = IntegerAtom.of(1);
        for (ASLObject argument : arguments) {
            ASLObject argValue = argument.evaluate(context);
            if (isNotNumeric(argValue))
                throw new Jump(MUL_JUMP);

            result = mul(result, argValue);
        }
        return result;
    }

    private NumericAtom<?> mul(NumericAtom<?> left, ASLObject right) {
        if (isInteger(left) && isInteger(right)) {
            return IntegerAtom.of(getInt(left) * getInt(right));
        } else if (isNumeric(right)) {
            return DoubleAtom.of(getDouble(left) * getDouble(right));
        }
        throw new Jump(CommonAttributes.MUL_JUMP);
    }
}
