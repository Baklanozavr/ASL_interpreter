package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.Undef;

public class IsUndef extends TypeCheckFunctionEvaluator {
    public static final String name = "isUndef";

    public IsUndef(FunctionCall f) {
        super(Undef.class, f);
    }
}
