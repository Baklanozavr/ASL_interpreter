package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import asl.model.core.Undef;
import asl.model.core.jumps.FunctionCallJump;
import org.jetbrains.annotations.NotNull;

/**
 * Функция conz имеет аргументы (x1, y1, …, xn, yn) и определяется следующим образом:
 * <li>Породить новый атрибутон u.
 * <li>Вычислить последовательно x1, y1, …, xn, yn.
 * <li>Пусть x1, y1, …, xn, yn возвращают значения ux1, uy1, …, uxn, uyn.
 * <li>Если yi возвращает значение undef, то удалить атрибут uxi атрибутона u.
 * <li>Если yi не возвращает значение undef, то присвоить атрибуту uxi атрибутона u значение uyi
 */
public class ConzFunction extends AbstractFunction {
    public static final ConzFunction INSTANCE = new ConzFunction();

    private ConzFunction() {
    }

    @Override
    protected @NotNull Thing getFunction(int argumentsNumber) {
        return new Body(argumentsNumber);
    }

    @Override
    protected @NotNull Context evalFunction(Context lc, GlobalContext gc) {
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
        protected @NotNull Context evalFunction(Context lc, GlobalContext gc) {
            if (argsNumber % 2 != 0)
                return lc.setJump(new FunctionCallJump());

            Attributon localVariables = lc.variables();
            Context parentContext = lc.parent();
            Attributon result = new Attributon();
            for (int i = 0; i < argsNumber / 2; i += 2) {
                Thing xi = localVariables.get(i);
                Context xiResult = xi.eval(parentContext, gc);
                Thing xJump = xiResult.jump();
                if (xJump.defined())
                    return lc.setJump(xJump);

                Thing yi = localVariables.get(i + 1);
                Context yiResult = yi.eval(parentContext, gc);
                Thing yJump = yiResult.jump();
                if (yJump.defined())
                    return lc.setJump(yJump);

                result.put(xiResult.value(), yiResult.value());
            }
            return lc.setValue(result);
        }
    }
}
