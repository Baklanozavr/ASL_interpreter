package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.NumericAtom;
import asl.model.core.StringAtom;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;
import static asl.model.util.MathUtils.getDouble;

public abstract class CompareFunction extends DefinedFunction {
    public CompareFunction(@NotNull FunctionCallEnum functionCallEnum, @NotNull List<ASLObject> arguments) {
        super(functionCallEnum, arguments);
    }

    abstract boolean falseCondition(double prev, double next);

    @Override
    public final @NotNull ASLObject evaluate(Context context) {
        int argSize = arguments.size();
        if (argSize > 1) {
            double prevX = getArgValue(0, context);
            for (int i = 1; i < argSize; ++i) {
                double nextX = getArgValue(i, context);
                if (falseCondition(prevX, nextX))
                    return FALSE;
                prevX = nextX;
            }
        }
        return TRUE;
    }

    private double getArgValue(int i, Context context) {
        ASLObject x = arguments.get(i).evaluate(context);
        if (x instanceof NumericAtom) return getDouble(x);
        if (x instanceof StringAtom) return ((StringAtom) x).value().length();
        throw new Jump(getJumpType());
    }
}
