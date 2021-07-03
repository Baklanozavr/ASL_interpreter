package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

/**
 * Функция jump для аргументов (x) определяется следующим образом: <br/>
 * $jumpType = x; $jumping = type;.
 * <p>
 * Функция jump для аргументов (x, y) определяется следующим образом: <br/>
 * $jumpType = x; $jumpValue = y; $jumping = type;
 */
public class JumpFunction extends FunctionEvaluator {
    public static final String name = "jump";

    public JumpFunction(FunctionCall f) {
        super(f);
        assertArgumentsSizeLessThan(3);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        switch (f.arguments.size()) {
            case 0:
                throw new Jump(Undef.UNDEF);
            case 1:
                throw new Jump(f.arguments.get(0));
            case 2:
                throw new Jump(f.arguments.get(0), f.arguments.get(1));
            default:
                throw new Jump(FUNCTION_CALL_JUMP);
        }
    }
}
