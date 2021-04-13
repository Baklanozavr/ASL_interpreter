package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция catch имеет аргументы (x, y1, …, yn) и определяется следующим образом:
 * Если s(lc, jumping) = undef, то возвратить текущее состояние.
 * Если s(lc, jumping) ≠ undef, то присвоить атрибуту jumping локального контекста значение undef.
 * Пусть s(lc, jumpType) = u, и s(lc, jumpValue) = v.
 * Вычислить x.
 * Если x возвращает джамп w, то возвратить джамп w.
 * Если x не возвращает джамп, то
 * Пусть x возвращает значение ux.
 * Если u = ux, то вычислить progn(y1, …, yn).
 * Если u не имеет тип ux, то возвратить джамп u типа u со значением v.
 */
public class CatchFunction extends DefinedFunction {
    public static final String name = "catch";

    public CatchFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(0);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Jump contextJump = context.getJump();
        if (contextJump == null)
            return Undef.UNDEF;

        context.setJump(null);
        ASLObject x = arguments.get(0).evaluate(context);
        if (contextJump.is(x)) {
            return new PrognFunction(arguments.subList(1, arguments.size())).evaluate(context);
        }
        throw contextJump;
    }
}
