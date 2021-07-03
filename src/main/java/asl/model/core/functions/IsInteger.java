package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;

public class IsInteger extends TypeCheckFunctionEvaluator {
    public static final String name = "isInteger";

    public IsInteger(FunctionCall f) {
        super(IntegerAtom.class, f);
    }
}
