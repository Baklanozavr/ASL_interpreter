package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public abstract class TypeFunctionEvaluator extends FunctionEvaluator {
    protected final Class<? extends ASLObject> type;

    public TypeFunctionEvaluator(@NotNull Class<? extends ASLObject> type, FunctionCall f) {
        super(f);
        this.type = type;
        assertArgumentsSize(1);
    }

    @NotNull
    protected ASLObject evalArgument(Context context) {
        return f.arguments.get(0).evaluate(context);
    }
}
