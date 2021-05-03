package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class SequenceFunction extends DefinedFunction {
    public SequenceFunction(@NotNull String name, @NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    @NotNull
    protected Sequence evalArgAsSequence(int argIndex, Context context) {
        ASLObject argument = arguments.get(argIndex).evaluate(context);
        return castToSequence(argument, argIndex);
    }

    @NotNull
    protected Sequence castToSequence(ASLObject evaluatedArgument, int argIndex) {
        Sequence sequence = SequenceFacade.toSequence(evaluatedArgument);
        if (sequence == null)
            throw new Jump(getJumpType(), "Argument with index " + argIndex + " is not a valid sequence");

        return sequence;
    }
}
