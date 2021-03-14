package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * Функция defun имеет аргументы (x, y, z) и определяется следующим образом:
 * <p>Пусть n – длина последовательности y.</p>
 * <p>Вычислить globaref(functions, x, n, conz(DefinedFunction, true, parameters, quote(y), body, quote(z)).</p>
 */
public final class DefunFunction extends DefinedFunction {

    public DefunFunction() {
        super(FunctionCallEnum.DEFUN, Collections.emptyList());
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        // todo: реализовать
        return null;
    }
}
