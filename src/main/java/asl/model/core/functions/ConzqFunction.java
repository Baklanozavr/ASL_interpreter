package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import asl.model.core.Undef;
import asl.model.core.jumps.ConzqJump;
import asl.model.core.jumps.FunctionCallJump;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.Attributes.*;

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
            if (argsNumber % 2 == 0)
                return lc.setJump(new FunctionCallJump());

            Attributon localVariables = lc.variables();
            Context parentContext = lc.parent();

            Thing x = localVariables.get(1);
            if (x.isNot(VARIABLE))
                return lc.setJump(new ConzqJump());

            Context evalResult = x.eval(parentContext, gc);
            Thing jump = evalResult.jump();
            if (jump.defined())
                return lc.setJump(jump);

            Thing target = evalResult.value();
            if (!(target instanceof Attributon))
                return lc.setJump(new ConzqJump());

            Attributon targetAttr = (Attributon) target;
            Thing attribute = null;
            for (int i = 2; i <= argsNumber; ++i) {
                x = localVariables.get(i);
                evalResult = x.eval(parentContext, gc);
                jump = evalResult.jump();
                if (jump.defined())
                    return lc.setJump(jump);

                if (i % 2 == 0)
                    attribute = evalResult.value();
                else
                    targetAttr.put(attribute, evalResult.value());
            }
            return lc.setValue(targetAttr);
        }
    }
}
