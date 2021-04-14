package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция isBoolean имеет аргументы (x) и определяется следующим образом:<br/>
 * Пусть x возвращает значение u.<br/>
 * Если u ∈ Boolean, то возвратить значение true.<br/>
 * Если u ∉ Boolean, то возвратить значение false.
 */
public class IsBoolean extends TypeCheckFunction {
    public static final String name = "isBoolean";

    public IsBoolean(@NotNull List<ASLObject> arguments) {
        super(BooleanAtom.class, name, arguments);
    }
}
