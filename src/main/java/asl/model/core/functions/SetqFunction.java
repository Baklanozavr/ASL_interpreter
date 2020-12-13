package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import asl.model.core.jumps.SetqJump;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.Attributes.VARIABLE;
import static asl.model.core.Undef.UNDEF;

/**
 * Функция присваивания переменной setq(x,y) - присваивает переменной x значение y (y вычисляется)
 */
public class SetqFunction extends AbstractFunction {
    public static final SetqFunction INSTANCE = new SetqFunction();

    private SetqFunction() {
    }

    @Override
    public @NotNull Thing getFunction(int argsNumber) {
        return argsNumber == 2 ? this : UNDEF;
    }

    @Override
    public @NotNull Context evalFunction(Context lc, GlobalContext gc) {
        Attributon localVariables = lc.variables();
        Thing x = localVariables.get(1); // get x
        if (x.isNot(VARIABLE))
            return lc.setJump(new SetqJump());

        Thing y = localVariables.get(2); // get y
        Context parentContext = lc.parent();
        Context yResult = y.eval(parentContext, gc); // evaluate y
        Thing yJump = yResult.jump();
        if (yJump.defined())
            return lc.setJump(yJump);

        Thing yValue = yResult.value();
        parentContext.variables().put(x, yValue);
        return lc.setValue(yValue);
    }
}
