package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция quote(x) определяется следующим образом:
 * <p>Возвратить значение x без его вычисления.</p>
 */
public final class QuoteFunction extends DefinedFunction {
    public static final String name = "quote";

    public QuoteFunction(List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return arguments.get(0);
    }
}
