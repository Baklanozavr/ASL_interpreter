package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isDouble;
import static asl.model.util.MathUtils.isInteger;

public final class Add extends MathFunction {
    public static final String name = "add";

    public Add(FunctionCall f) {
        super(f);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull NumericAtom<?> evaluate(Context context) {
        List<Integer> intValues = new ArrayList<>();
        List<Double> doubleValues = new ArrayList<>();

        for (ASLObject argument : f.arguments) {
            ASLObject x = argument.evaluate(context);
            if (isInteger(x)) {
                intValues.add(getInt(x));
            } else if (isDouble(x)) {
                doubleValues.add(getDouble(x));
            } else {
                throw new Jump(getJumpType());
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
