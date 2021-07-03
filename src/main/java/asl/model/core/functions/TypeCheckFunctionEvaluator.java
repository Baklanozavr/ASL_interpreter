package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

public abstract class TypeCheckFunctionEvaluator extends TypeFunctionEvaluator {
    public TypeCheckFunctionEvaluator(@NotNull Class<? extends ASLObject> typeToCheck, FunctionCall f) {
        super(typeToCheck, f);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject argument = evalArgument(context);
        return type.isInstance(argument) ? TRUE : FALSE;
    }
}
