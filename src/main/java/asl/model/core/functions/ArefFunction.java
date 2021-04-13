package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * p
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
public final class ArefFunction extends DefinedFunction {
    public static final String name = "aref";

    public ArefFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = arguments.get(0).evaluate(context);
        for (int i = 1; i < arguments.size(); ++i) {
            if (!(argValue instanceof ASLObjectWithAttributes))
                throw new Jump(getJumpType());

            ASLObject nextAttribute = arguments.get(i).evaluate(context);
            argValue = ((ASLObjectWithAttributes) argValue).get(nextAttribute);
        }
        return argValue;
    }
}
