package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.NumericAtom;
import asl.model.system.Context;

public abstract class MathFunction extends FunctionEvaluator {
    protected MathFunction(FunctionCall f) {
        super(f);
    }

    protected NumericAtom<?> evalNumericArgument(int argNumber, Context context) {
        return evalArgAs(argNumber, context, NumericAtom.class);
    }
}
