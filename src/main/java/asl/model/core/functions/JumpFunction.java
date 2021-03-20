package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

/**
 * Функция jump для аргументов (x) определяется следующим образом: <br/>
 * $jumpType = x; $jumping = type;.
 * <p>
 * Функция jump для аргументов (x, y) определяется следующим образом: <br/>
 * $jumpType = x; $jumpValue = y; $jumping = type;
 */
public class JumpFunction extends DefinedFunction {
    public JumpFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.JUMP, arguments);
        assertArgumentsSizeLessThan(3);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        switch (arguments.size()) {
            case 0:
                throw new Jump(Undef.UNDEF);
            case 1:
                throw new Jump(arguments.get(0));
            case 2:
                throw new Jump(arguments.get(0), arguments.get(1));
            default:
                throw new Jump(FUNCTION_CALL_JUMP);
        }
    }
}
