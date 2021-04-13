package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLVariable;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.SETQ_JUMP;

/**
 * Функция присваивания переменной setq(x,y) - присваивает переменной x значение y (y вычисляется)
 */
public final class SetqFunction extends DefinedFunction {
    public static final String name = "setq";

    public SetqFunction(List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject variable_ = arguments.get(0);
        ASLObject value = arguments.get(1);

        if (!(variable_ instanceof ASLVariable))
            throw new Jump(SETQ_JUMP);

        ASLObject evaluatedValue = value.evaluate(context);
        ((ASLVariable) variable_).setToContext(context, evaluatedValue);
        return evaluatedValue;
    }
}
