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

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isInteger;
import static asl.model.util.MathUtils.isNumeric;
import static asl.model.util.MathUtils.mathCollector;

public final class AddFunction extends DefinedFunction {
    public AddFunction(List<ASLObject> arguments) {
        super(FunctionCallEnum.ADD, arguments);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull NumericAtom<?> evaluate(Context context) {
        return evaluateArguments(context)
                .stream()
                .collect(mathCollector(IntegerAtom::zero, this::add));
    }

    private NumericAtom<?> add(ASLObject left, ASLObject right) {
        if (isInteger(left) && isInteger(right)) {
            return IntegerAtom.of(getInt(left) + getInt(right));
        } else if (isNumeric(left) && isNumeric(right)) {
            return DoubleAtom.of(getDouble(left) + getDouble(right));
        }
        throw new Jump(CommonAttributes.ADD_JUMP);
    }
}
