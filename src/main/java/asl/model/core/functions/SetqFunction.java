package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;
import asl.model.core.jumps.SetqJump;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.Attributes.BODY;
import static asl.model.core.Attributes.VARIABLE;
import static asl.model.core.Undef.UNDEF;

public class SetqFunction extends Attributon {
    private static final QNameAtom X = QNameAtom.create("x");
    private static final QNameAtom Y = QNameAtom.create("y");

    SetqFunction() {
        put(1, X);
        put(2, Y);
        put(BODY, new Body());
    }

    private static class Body extends Attributon {
        @Override
        public @NotNull Context eval(Context lc, GlobalContext gc) {
            Thing x = lc.variables().get(X);
            if (x.is(VARIABLE)) {
                Thing y = lc.variables().get(Y);
                Context yResult = y.eval(lc.parent(), gc);
                Thing yJump = yResult.jump();
                if (!yJump.equals(UNDEF)) {
                    return lc.setJump(yJump);
                }
                Thing yValue = yResult.value();
                lc.parent().variables().put(x, yValue);
                return lc.setValue(yValue);
            }
            return lc.setJump(new SetqJump());
        }
    }
}
