package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.system.ASLObjectsFactory.makeFunctionCall;

/**
 * Функция seqApply имеет аргументы (x, y) и определяется следующим образом:
 * Пусть x, y возвращают значения ux, uy.
 * Если ux – не функция, то возвратить джамп типа seqApplyJump.
 * Если ux – функция, и uy – последовательность длины n, то
 * Если ux допускает последовательность аргументов длины n, то применить ux к последовательности аргументов,
 * соответствующих элементам последовательности uy.
 * Если ux не допускает последовательность аргументов длины n, то возвратить джамп типа seqApplyJump
 */
public class SeqApply extends SequenceFunction {
    public static final String name = "seqApply";

    public SeqApply(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        String functionName = evalArgAs(0, context, QNameAtom.class).value();
        var sequenceElements = evalArgAsSequence(1, context).toList();
        ASLObject functionCall;
        try {
            functionCall = makeFunctionCall(functionName, sequenceElements);
        } catch (Jump callJump) {
            throw new Jump(getJumpType(), callJump.getValue());
        }
        // вычисление снаружи блока try/catch, чтобы не ловить Jump от вычислений
        return functionCall.evaluate(context);
    }
}
