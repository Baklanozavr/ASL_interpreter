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

import java.util.ArrayList;
import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isDouble;
import static asl.model.util.MathUtils.isInteger;

public final class AddFunction extends MathFunction {
    public AddFunction(List<ASLObject> arguments) {
        super(FunctionCallEnum.ADD, arguments);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull NumericAtom<?> evaluate(Context context) {
        List<Integer> intValues = new ArrayList<>();
        List<Double> doubleValues = new ArrayList<>();

        for (ASLObject argument : arguments) {
            ASLObject x = argument.evaluate(context);
            if (isInteger(x)) {
                intValues.add(getInt(x));
            } else if (isDouble(x)) {
                doubleValues.add(getDouble(x));
            } else {
                throw new Jump(CommonAttributes.ADD_JUMP);
            }
        }

        int intSum = intValues.stream().mapToInt(Integer::intValue).sum();
        if (doubleValues.isEmpty()) {
            return IntegerAtom.of(intSum);
        }
        double doubleSum = doubleValues.stream().mapToDouble(Double::doubleValue).sum();
        return DoubleAtom.of(intSum + doubleSum);
    }
}
