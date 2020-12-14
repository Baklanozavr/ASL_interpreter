package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import asl.model.core.Undef;
import asl.model.core.jumps.FunctionCallJump;
import org.jetbrains.annotations.NotNull;

/**
 * Функция conzq имеет аргументы (x, y1, z1, …, yn, zn) и определяется следующим образом:
 * <li>Если x не переменная, возвращает jump типа conzQJump.
 * <li>Если x - переменная, которая имеет в качестве значения undef или атом, вернуть conzQJump.
 * <li>Если x - переменная, которая имеет в качестве значения аттрибутон u, выполнить последовательные присваивания:
 * u.y1 = z1; ...; u.yn = zn.
 */
public class ConzqFunction extends AbstractFunction {
    public static final ConzqFunction INSTANCE = new ConzqFunction();

    private ConzqFunction() {
    }

    @Override
    protected @NotNull Thing getFunction(int argumentsNumber) {
        return new Body(argumentsNumber);
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
