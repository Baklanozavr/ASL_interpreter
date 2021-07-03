package asl.model.core.functions;

import asl.model.core.Atom;
import asl.model.core.FunctionCall;

/**
 * Функция isAtom имеет аргументы (x) и определяется следующим образом:<br/>
 * Пусть x возвращает значение u.<br/>
 * Если u ∈ Atom, то возвратить значение true.<br/>
 * Если u ∉ Atom, то возвратить значение false
 */
public final class IsAtom extends TypeCheckFunctionEvaluator {
    public static final String name = "isAtom";

    public IsAtom(FunctionCall f) {
        super(Atom.class, f);
    }
}
