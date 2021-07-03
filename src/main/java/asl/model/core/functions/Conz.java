package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.PlainAttributon;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция conz имеет аргументы (x1, y1, …, xn, yn) и определяется следующим образом:
 * <li> Породить новый атрибутон u.
 * <li> Вычислить последовательно x1, y1, …, xn, yn.
 * <li> Пусть x1, y1, …, xn, yn возвращают значения ux1, uy1, …, uxn, uyn.
 * <li> Если yi возвращает значение undef, то удалить атрибут uxi атрибутона u.
 * <li> Если yi не возвращает значение undef, то присвоить атрибуту uxi атрибутона u значение uyi
 * <p>
 * Для нечётного количества аргументов последний будет отброшен
 */
public final class Conz extends FunctionEvaluator {
    public static final String name = "conz";

    public Conz(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        List<ASLObject> evaluatedArgs = evaluateArguments(context);

        int argsNumber = evaluatedArgs.size();
        PlainAttributon attributon = new PlainAttributon(argsNumber / 2);
        for (int i = 0; i < argsNumber; i += 2) {
            attributon.put(evaluatedArgs.get(i), evaluatedArgs.get(i + 1));
        }
        return attributon;
    }
}
