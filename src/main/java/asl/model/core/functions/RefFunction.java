package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция aref имеет аргументы (x0, x1, …, xn) и определяется следующим образом:
 * <li> Пусть x0 возвращает значение u0.
 * <li> Если n = 0, то возвратить значение u0.
 * <li> Если n > 0, то
 * <li> Если u0 ∉ Attributon, то возвратить джамп типов arefJump и Undef.
 * <li> Если u0 ∈ Attributon, то.
 * <li> Пусть выражения x1, …, xn возвращают значения u1, …, un.
 * <li> Пусть vi = av(ui-1, ui).
 * <li> Если v1, …, vi-1 ∈ Attributon, vi ∉ Attributon, и 1≤ i<n, то xi+1, …, xn не вычислять, и возвратить джамп типов arefJump и Undef.
 * <li> Если v1, …, vn-1 ∈ Attributon, то возвратить значение vn.
 */
public class RefFunction extends FunctionEvaluator {
    public static final String aref = "aref";
    public static final String globaref = "globaref";
    public static final String interef = "interef";

    public RefFunction(FunctionCall f) {
        super(f);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = f.arguments.get(0).evaluate(context);
        for (int i = 1; i < f.arguments.size(); ++i) {
            if (!(argValue instanceof ASLObjectWithAttributes))
                throw new Jump(getJumpType());

            ASLObject nextAttribute = f.arguments.get(i).evaluate(context);
            argValue = ((ASLObjectWithAttributes) argValue).get(nextAttribute);
        }
        return argValue;
    }
}
