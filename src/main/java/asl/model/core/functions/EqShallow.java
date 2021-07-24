package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public class EqShallow extends FunctionEvaluator {
    public static final String name = "eqShallow";

    public EqShallow(FunctionCall f) {
        super(f);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject first = f.arguments.get(0).evaluate(context);
        for (int i = 1; i < f.arguments.size(); ++i) {
            ASLObject next = f.arguments.get(i).evaluate(context);
            if (!first.equalsShallow(next))
                return BooleanAtom.FALSE;
        }
        return BooleanAtom.TRUE;
    }
}
