package asl.model.core.functions;

import asl.model.core.DoubleAtom;
import asl.model.core.FunctionCall;

public class IsDouble extends TypeCheckFunctionEvaluator {
    public static final String name = "isDouble";

    public IsDouble(FunctionCall f) {
        super(DoubleAtom.class, f);
    }
}
