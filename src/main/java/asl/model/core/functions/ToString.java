package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.core.StringAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция toString имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Пусть w – результат приведения u к String.
 * Если u приводится к String, то возвратить значение w.
 * Если u не приводится к String, то возвратить джамп типа toStringJump.
 */
public class ToString extends TypeCastFunction<StringAtom> {
    public static final String name = "toString";

    public ToString(@NotNull List<ASLObject> arguments) {
        super(StringAtom.class, name, arguments);
    }

    @Override
    protected StringAtom additionalCast(ASLObject argValue) {
        if (argValue instanceof QNameAtom) return new StringAtom(((QNameAtom) argValue).value());
        throw new Jump(getJumpType());
    }
}
