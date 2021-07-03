package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.NumericAtom;

public class IsNumeric extends TypeCheckFunctionEvaluator {
    public static final String name = "isNumeric";

    public IsNumeric(FunctionCall f) {
        super(NumericAtom.class, f);
    }
}
