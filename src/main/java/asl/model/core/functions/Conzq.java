package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.ASLVariable;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;
import static asl.model.core.CommonAttributes.SETQ_JUMP;

/**
 * Функция conzq имеет аргументы (x, y1, z1, …, yn, zn) и определяется следующим образом:
 * <li> Если x не переменная, возвращает jump типа conzQJump.
 * <li> Если x - переменная, которая имеет в качестве значения undef или атом, вернуть conzQJump.
 * <li> Если x - переменная, которая имеет в качестве значения аттрибутон u, выполнить последовательные присваивания:
 * u.y1 = z1; ...; u.yn = zn.
 * <p>
 * Последний элемент будет проигнорирован, если количество аргументов чётное
 */
public final class Conzq extends FunctionEvaluator {
    public static final String name = "conzq";

    public Conzq(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        int argsSize = f.arguments.size();
        if (argsSize % 2 == 0)
            throw new Jump(FUNCTION_CALL_JUMP);

        ASLObject firstArgument = f.arguments.get(0);
        if (!(firstArgument instanceof ASLVariable))
            throw new Jump(SETQ_JUMP);

        ASLObject variableValue = firstArgument.evaluate(context);
        if (!(variableValue instanceof ASLObjectWithAttributes))
            throw new Jump(SETQ_JUMP);

        var attributon = (ASLObjectWithAttributes) variableValue;
        for (int i = 1; i < argsSize; i += 2) {
            ASLObject attrKey = f.arguments.get(i).evaluate(context);
            ASLObject attrValue = f.arguments.get(i + 1).evaluate(context);
            attributon.put(attrKey, attrValue);
        }
        return attributon;
    }
}
