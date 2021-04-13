package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.NumericAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class MathFunction extends DefinedFunction {
    public MathFunction(@NotNull String name, @NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    protected NumericAtom<?> evalNumericArgument(int argNumber, Context context) {
        return evalArgAs(argNumber, context, NumericAtom.class);
    }
}
