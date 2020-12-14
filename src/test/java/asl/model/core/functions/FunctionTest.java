package asl.model.core.functions;

import asl.ASLTest;
import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;

public abstract class FunctionTest implements ASLTest {

    protected static Context evalFunc(QNameAtom functionName, Context context, Thing... args) {
        setArguments(context, args);
        return GC.getFunction(functionName, args.length).eval(context, GC);
    }

    protected static void setArguments(Context context, Thing... args) {
        Attributon variables = context.variables();
        for (int i = 0; i < args.length; ++i) {
            variables.put(i + 1, args[i]);
        }
    }
}
