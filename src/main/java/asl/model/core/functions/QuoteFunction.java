package asl.model.core.functions;

import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.Undef.UNDEF;

/**
 * Функция quote(x) определяется следующим образом:
 * <p>Возвратить значение x.</p>
 */
public class QuoteFunction extends AbstractFunction {
    public static final QuoteFunction INSTANCE = new QuoteFunction();

    private QuoteFunction() {
    }

    @Override
    protected @NotNull Thing getFunction(int argumentsNumber) {
        return argumentsNumber == 1 ? this : UNDEF;
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        Thing x = lc.variables().get(1); // get x
        Context xResult = x.eval(lc.parent(), gc); // evaluate x in parent context
        Thing xJump = xResult.jump();
        return xJump.defined() ?
                lc.setJump(xJump) :
                lc.setValue(xResult.value());
    }
}
