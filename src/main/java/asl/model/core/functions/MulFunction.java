package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.CommonAttributes;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isDouble;
import static asl.model.util.MathUtils.isInteger;

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
        List<Integer> intValues = new ArrayList<>();
        List<Double> doubleValues = new ArrayList<>();

        for (ASLObject argument : arguments) {
            ASLObject x = argument.evaluate(context);
            if (isInteger(x)) {
                intValues.add(getInt(x));
            } else if (isDouble(x)) {
                doubleValues.add(getDouble(x));
            } else {
                throw new Jump(CommonAttributes.MUL_JUMP);
            }
        }

        int intResult = intValues.stream().mapToInt(Integer::intValue).reduce(1, Math::multiplyExact);
        if (doubleValues.isEmpty()) {
            return IntegerAtom.of(intResult);
        }
        double doubleResult = doubleValues.stream().mapToDouble(Double::doubleValue).reduce(1, (x, y) -> x * y);
        return DoubleAtom.of(intResult * doubleResult);
    }
}
