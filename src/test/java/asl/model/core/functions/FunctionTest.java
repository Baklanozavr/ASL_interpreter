package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;

public abstract class FunctionTest {
    protected static final GlobalContext GC = new GlobalContext();

    protected static void setArguments(Context context, Thing... args) {
        Attributon variables = context.variables();
        for (int i = 0; i < args.length; ++i) {
            variables.put(i + 1, args[i]);
        }
    }
}
