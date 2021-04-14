package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TypeFunction extends DefinedFunction {
    protected final Class<? extends ASLObject> type;

    public TypeFunction(@NotNull Class<? extends ASLObject> type,
                        @NotNull String name,
                        @NotNull List<ASLObject> arguments) {
        super(name, arguments);
        this.type = type;
        assertArgumentsSize(1);
    }

    @NotNull
    protected ASLObject evalArgument(Context context) {
        return arguments.get(0).evaluate(context);
    }
}
