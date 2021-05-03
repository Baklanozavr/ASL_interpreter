package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция seqCopy имеет аргументы (x) и эквивалентна copyShallow(x)
 */
public class SeqCopy extends SequenceFunction {
    public static final String name = "seqCopy";

    public SeqCopy(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        // todo: implement later
        throw new IllegalStateException("This method is not implemented yet");
    }
}
