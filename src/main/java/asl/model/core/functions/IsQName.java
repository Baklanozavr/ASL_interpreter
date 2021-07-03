package asl.model.core.functions;

import asl.model.core.FunctionCall;
import asl.model.core.QNameAtom;

public class IsQName extends TypeCheckFunctionEvaluator {
    public static final String name = "isQName";

    public IsQName(FunctionCall f) {
        super(QNameAtom.class, f);
    }
}
