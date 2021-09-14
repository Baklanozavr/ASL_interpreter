package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public class CopyDeep extends FunctionEvaluator {
    public static final String name = "copyDeep";

    public CopyDeep(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return f.arguments.get(0).evaluate(context).copyDeep();
    }
}
