package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TypeCheckFunction extends DefinedFunction {
    private final Class<? extends ASLObject> typeToCheck;

    public TypeCheckFunction(@NotNull Class<? extends ASLObject> typeToCheck,
                             @NotNull String name,
                             @NotNull List<ASLObject> arguments) {
        super(name, arguments);
        this.typeToCheck = typeToCheck;
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argument = arguments.get(0).evaluate(context);
        return typeToCheck.isInstance(argument) ? BooleanAtom.TRUE : BooleanAtom.FALSE;
    }
}
