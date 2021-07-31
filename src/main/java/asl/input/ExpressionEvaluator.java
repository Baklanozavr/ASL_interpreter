package asl.input;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/** Evaluates an ASL-expression into a {@link Context} */
public class ExpressionEvaluator implements ASLConsumer {
    private final Context context;

    public ExpressionEvaluator(@NotNull Context initialContext) {
        context = initialContext;
    }

    @Override
    public void consume(ASLObject expr) {
        expr.evaluateToContext(context);
    }
}
