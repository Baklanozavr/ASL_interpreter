package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Функция copyShallow имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u ∉ Attributon, то возвратить значение u.
 * Если u ∈ Attributon, то породить новый атрибутон u1,
 * определить те же самые атрибуты у u1 как у u, и присвоить им те же самые значения.
 * Возвратить значение u1.
 */
public class CopyShallow extends FunctionEvaluator {
    public static final String name = "copyShallow";
    public static final String seqCopy = "seqCopy"; // Функция seqCopy эквивалентна copyShallow
    public static final String copySeq = "copySeq"; // Функция copySeq эквивалентна copyShallow

    public CopyShallow(FunctionCall f) {
        super(f);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = f.arguments.get(0).evaluate(context);
        if (!(argValue instanceof ASLObjectWithAttributes))
            return argValue;


        return argValue;
    }
}
