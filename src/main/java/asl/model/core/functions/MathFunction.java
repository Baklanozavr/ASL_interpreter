package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.core.QNameAtom;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class MathFunction extends DefinedFunction {
    protected MathFunction(@NotNull FunctionCallEnum functionCallEnum, @NotNull List<ASLObject> arguments) {
        super(functionCallEnum, arguments);
    }

    protected NumericAtom<?> getNumericArgument(int argNumber, Context context, QNameAtom jumpType) {
        ASLObject x = arguments.get(argNumber).evaluate(context);
        if (x instanceof NumericAtom<?>) {
            return (NumericAtom<?>) x;
        }
        throw new Jump(jumpType);
    }
}
