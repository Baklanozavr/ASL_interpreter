package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция isDef имеет аргументы (x) и определяется следующим образом:<br/>
 * Если x не возвращает значение undef, то возвратить значение true.<br/>
 * Если x возвращает значение undef, то возвратить значение false.
 */
public class IsDef extends TypeCheckFunction {
    public static final String name = "isDef";

    public IsDef(@NotNull List<ASLObject> arguments) {
        super(Undef.class, name, arguments);
    }

    @Override
    public @NotNull BooleanAtom evaluate(Context context) {
        return super.evaluate(context) == TRUE ? FALSE : TRUE;
    }
}
