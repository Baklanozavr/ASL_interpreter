package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Аргументы принимаются как есть
 * <p>
 * Принимает 4 аргумента:
 * - переменная для атрибута
 * - переменная для значения атрибута
 * - аттрибутон для обхода
 * - function body
 */
public class ForEach extends AbstractForEach {
    public static final String name = "forEach";

    public ForEach(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObjectWithAttributes attrObj = evalArgAs(2, context, ASLObjectWithAttributes.class);
        attrObj.getAttributes().forEach(getKeyValueConsumer(context));
        return Undef.UNDEF;
    }
}
