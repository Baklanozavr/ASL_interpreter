package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.StringAtom;

public class IsString extends TypeCheckFunctionEvaluator {
    public static final String name = "isString";

    public IsString(FunctionCall f) {
        super(StringAtom.class, f);
    }
}
