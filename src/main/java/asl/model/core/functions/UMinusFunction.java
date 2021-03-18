package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.UMINUS_JUMP;
import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isInteger;

/**
 * Функция uminus имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * <li> Если u ∈ Integer ∪ Double, то возвратить значение, которое является отрицанием числа u.
 * <li> Если u ∉ Integer ∪ Double, то возвратить джамп типа uminusJump.
 */
public class UMinusFunction extends MathFunction {
    protected UMinusFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.UMINUS, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject x = getNumericArgument(0, context, UMINUS_JUMP);
        return isInteger(x) ? IntegerAtom.of(-getInt(x)) : DoubleAtom.of(-getDouble(x));
    }
}
