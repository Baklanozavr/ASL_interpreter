package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

public abstract class TypeCheckFunction extends TypeFunction {
    public TypeCheckFunction(@NotNull Class<? extends ASLObject> typeToCheck,
                             @NotNull String name,
                             @NotNull List<ASLObject> arguments) {
        super(typeToCheck, name, arguments);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject argument = evalArgument(context);
        return type.isInstance(argument) ? TRUE : FALSE;
    }
}
