package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public abstract class TypeCastFunctionEvaluator<T extends ASLObject> extends TypeFunctionEvaluator {
    public TypeCastFunctionEvaluator(@NotNull Class<T> typeToCast, FunctionCall f) {
        super(typeToCast, f);
    }

    @Override
    public @NotNull T evaluate(Context context) {
        ASLObject argument = evalArgument(context);
        //noinspection unchecked
        return type.isInstance(argument) ?
                (T) argument :
                additionalCast(argument);
    }

    protected abstract T additionalCast(ASLObject argValue);
}
