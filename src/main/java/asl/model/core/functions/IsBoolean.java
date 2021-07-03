package asl.model.core.functions;

import asl.model.core.BooleanAtom;
import asl.model.core.FunctionCall;

/**
 * Функция isBoolean имеет аргументы (x) и определяется следующим образом:<br/>
 * Пусть x возвращает значение u.<br/>
 * Если u ∈ Boolean, то возвратить значение true.<br/>
 * Если u ∉ Boolean, то возвратить значение false.
 */
public class IsBoolean extends TypeCheckFunctionEvaluator {
    public static final String name = "isBoolean";

    public IsBoolean(FunctionCall f) {
        super(BooleanAtom.class, f);
    }
}
