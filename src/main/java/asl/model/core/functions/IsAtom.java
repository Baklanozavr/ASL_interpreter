package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Atom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция isAtom имеет аргументы (x) и определяется следующим образом:<br/>
 * Пусть x возвращает значение u.<br/>
 * Если u ∈ Atom, то возвратить значение true.<br/>
 * Если u ∉ Atom, то возвратить значение false
 */
public final class IsAtom extends TypeCheckFunction {
    public static final String name = "isAtom";

    public IsAtom(@NotNull List<ASLObject> arguments) {
        super(Atom.class, name, arguments);
    }
}
