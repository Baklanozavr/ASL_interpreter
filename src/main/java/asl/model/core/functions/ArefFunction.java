package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import asl.model.core.Undef;
import asl.model.core.jumps.ArefJump;
import org.jetbrains.annotations.NotNull;

/**
 * Функция aref имеет аргументы (x0, x1, …, xn) и определяется следующим образом:
 * <li>Пусть x0 возвращает значение u0.
 * <li>Если n = 0, то возвратить значение u0.
 * <li>Если n > 0, то
 * <li>Если u0 ∉ Attributon, то возвратить джамп типов arefJump и Undef.
 * <li>Если u0 ∈ Attributon, то.
 * <li>Пусть выражения x1, …, xn возвращают значения u1, …, un.
 * <li>Пусть vi = av(ui-1, ui).
 * <li>Если v1, …, vi-1 ∈ Attributon, vi ∉ Attributon, и 1≤ i<n, то xi+1, …, xn не вычислять, и возвратить джамп типов arefJump и Undef.
 * <li>Если v1, …, vn-1 ∈ Attributon, то возвратить значение vn.
 */
public class ArefFunction extends AbstractFunction {
    public static final ArefFunction INSTANCE = new ArefFunction();

    private ArefFunction() {
    }

    @Override
    protected @NotNull Thing getFunction(int argumentsNumber) {
        return argumentsNumber > 0
                ? new Body(argumentsNumber)
                : Undef.UNDEF;
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        throw new IllegalStateException("Unexpected eval call!");
    }

    private static class Body extends AbstractFunction {
        private final int argsNumber;

        private Body(int argsNumber) {
            this.argsNumber = argsNumber;
        }

        @Override
        protected @NotNull Thing getFunction(int argumentsNumber) {
            return Undef.UNDEF;
        }

        @Override
        public @NotNull Context eval(Context lc, GlobalContext gc) {
            Attributon localVariables = lc.variables();
            Context parentContext = lc.parent();

            Thing x = localVariables.get(1);
            Context result = x.eval(parentContext, gc);
            Thing jump = result.jump();
            if (jump.defined())
                return lc.setJump(jump);

            Thing currentValue = result.value();
            if (argsNumber == 1)
                return lc.setValue(currentValue);

            if (!(currentValue instanceof Attributon))
                return lc.setJump(new ArefJump());

            Attributon prevAttr = (Attributon) currentValue;
            for (int i = 2; i <= argsNumber; ++i) {
                x = localVariables.get(i);
                result = x.eval(parentContext, gc);
                jump = result.jump();
                if (jump.defined())
                    return lc.setJump(jump);

                currentValue = prevAttr.get(result.value());
                if (i != argsNumber) {
                    if (!(currentValue instanceof Attributon))
                        return lc.setJump(new ArefJump());

                    prevAttr = (Attributon) currentValue;
                }
            }
            return lc.setValue(currentValue);
        }
    }
}
