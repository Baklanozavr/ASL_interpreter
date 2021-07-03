package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isInteger;

/**
 * Функция uminus имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * <li> Если u ∈ Integer ∪ Double, то возвратить значение, которое является отрицанием числа u.
 * <li> Если u ∉ Integer ∪ Double, то возвратить джамп типа uminusJump.
 */
public class UMinus extends MathFunction {
    public static final String name = "uminus";

    public UMinus(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        var x = evalNumericArgument(0, context);
        return isInteger(x) ? IntegerAtom.of(-getInt(x)) : DoubleAtom.of(-getDouble(x));
    }
}
