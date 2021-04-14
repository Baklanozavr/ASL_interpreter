package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TypeCastFunction<T extends ASLObject> extends TypeFunction {
    public TypeCastFunction(@NotNull Class<T> typeToCast,
                            @NotNull String name,
                            @NotNull List<ASLObject> arguments) {
        super(typeToCast, name, arguments);
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
