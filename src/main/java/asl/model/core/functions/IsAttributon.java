package asl.model.core.functions;

import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.FunctionCall;

public class IsAttributon extends TypeCheckFunctionEvaluator {
    public static final String name = "isAttributon";

    public IsAttributon(FunctionCall f) {
        super(ASLObjectWithAttributes.class, f);
    }
}
