package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Evaluates its arguments, starting from the first one, while the results of evaluations are undef-s.
 * First non-undef result is the result of the call;
 * the arguments after the found non-undef are not evaluated;
 * zero arguments or all undef arguments results in an undef.
 */
public class Coalesce extends FunctionEvaluator {
    public static final String name = "coalesce";

    public Coalesce(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        for (ASLObject argument : f.arguments) {
            ASLObject currentArgValue = argument.evaluate(context);
            if (currentArgValue != Undef.UNDEF) return currentArgValue;
        }
        return Undef.UNDEF;
    }
}
