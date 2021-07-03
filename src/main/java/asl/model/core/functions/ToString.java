package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.StringAtom;

/**
 * Функция toString имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Пусть w – результат приведения u к String.
 * Если u приводится к String, то возвратить значение w.
 * Если u не приводится к String, то возвратить джамп типа toStringJump.
 */
public class ToString extends TypeCastFunctionEvaluator<StringAtom> {
    public static final String name = "toString";

    public ToString(FunctionCall f) {
        super(StringAtom.class, f);
    }

    @Override
    protected StringAtom additionalCast(ASLObject argValue) {
        return new StringAtom(argValue.toString());
    }
}
