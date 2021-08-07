package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.LocalVariable;
import asl.model.system.Context;

import java.util.function.BiConsumer;

/**
 * Аргументы принимаются как есть
 * <p>
 * Принимает 4 аргумента:
 * - переменная для атрибута
 * - переменная для значения атрибута
 * - объект с атрибутами для обхода
 * - function body
 */
public abstract class AbstractForEach extends FunctionEvaluator {
    public AbstractForEach(FunctionCall f) {
        super(f);
        assertArgumentsSize(4);
    }

    protected BiConsumer<ASLObject, ASLObject> getKeyValueConsumer(Context context) {
        LocalVariable keyVar = getArgAs(0, LocalVariable.class);
        LocalVariable valueVar = getArgAs(1, LocalVariable.class);
        FunctionCall function = getArgAs(3, FunctionCall.class);
        return (k, v) -> {
            keyVar.setToContext(context, k);
            valueVar.setToContext(context, v);
            function.evaluate(context);
        };
    }
}
