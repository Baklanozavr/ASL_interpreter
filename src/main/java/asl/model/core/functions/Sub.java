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
 * Функция sub имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * <li> Если ux, uy ∈ Integer ∪ Double, то возвратить значение, которое является разностью w чисел ux и uy.
 * <li> Если ux, uy ∈ Integer, то w ∈ Integer.
 * <li> Если  ux ∈ Double, или uy ∈ Double, то w ∈ Double.
 * <li> Если ux ∉ Integer ∪ Double, то не вычислять y и возвратить джамп типа subJump.
 * <li> Если ux ∈ Integer ∪ Double, и uy ∉ Integer ∪ Double, то возвратить джамп типа subJump.
 */
public class Sub extends MathFunction {
    public static final String name = "sub";

    public Sub(FunctionCall f) {
        super(f);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        var x = evalNumericArgument(0, context);
        var y = evalNumericArgument(1, context);
        return isInteger(x) && isInteger(y) ?
                IntegerAtom.of(getInt(x) - getInt(y)) :
                DoubleAtom.of(getDouble(x) - getDouble(y));
    }
}
