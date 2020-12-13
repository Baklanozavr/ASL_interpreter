package asl.model.core.functions;

import asl.model.core.Attributon;

/**
 * Функция defun имеет аргументы (x, y, z) и определяется следующим образом:
 * <p>Пусть n – длина последовательности y.</p>
 * <p>Вычислить globaref(functions, x, n, conz(DefinedFunction, true, parameters, quote(y), body, quote(z)).</p>
 */
public class DefunFunction extends Attributon {
    public static final DefunFunction INSTANCE = new DefunFunction();

    private DefunFunction() {
    }


}
