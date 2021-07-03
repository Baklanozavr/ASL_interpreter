package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция quote(x) определяется следующим образом:
 * <p>Возвратить значение x без его вычисления.</p>
 */
public final class Quote extends FunctionEvaluator {
    public static final String name = "quote";

    public Quote(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return f.arguments.get(0);
    }
}
