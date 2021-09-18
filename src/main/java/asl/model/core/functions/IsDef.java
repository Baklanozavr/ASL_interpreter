package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция {@code isDef} имеет аргументы (x) и определяется следующим образом:
 * <br/> Если x не возвращает значение {@code undef}, то возвратить значение {@code true}.
 * <br/> Если x возвращает значение {@code undef}, то возвратить значение {@code false}.
 */
public class IsDef extends FunctionEvaluator {
    public static final String name = "isDef";

    public IsDef(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        ASLObject arg = f.arguments.get(0).evaluate(context);
        return arg == Undef.UNDEF ? FALSE : TRUE;
    }
}
