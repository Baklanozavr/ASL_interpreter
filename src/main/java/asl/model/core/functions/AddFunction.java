package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.IntegerAtom;
import asl.model.core.Numeric;
import asl.model.core.Thing;
import asl.model.core.Undef;
import asl.model.core.jumps.AddJump;
import org.jetbrains.annotations.NotNull;

public class AddFunction extends AbstractFunction {
    public static final AddFunction INSTANCE = new AddFunction();

    private AddFunction() {
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        throw new IllegalStateException("Unexpected eval call!");
    }

    @Override
    protected @NotNull Thing getFunction(int argumentsNumber) {
        return new Body(argumentsNumber);
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

            Numeric currentValue = new IntegerAtom(0);
            for (int i = 1; i <= argsNumber; ++i) {
                Thing x = localVariables.get(i);
                Context result = x.eval(parentContext, gc);
                Thing jump = result.jump();
                if (jump.defined())
                    return lc.setJump(jump);

                Thing xValue = result.value();
                if (!(xValue instanceof Numeric))
                    return lc.setJump(new AddJump());

                currentValue = currentValue.plus((Numeric) xValue);
            }
            return lc.setValue((Thing) currentValue);
        }
    }

}
