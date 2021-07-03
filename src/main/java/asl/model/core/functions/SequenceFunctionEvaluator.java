package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

public abstract class SequenceFunctionEvaluator extends FunctionEvaluator {
    public SequenceFunctionEvaluator(FunctionCall f) {
        super(f);
    }

    @NotNull
    protected Sequence evalArgAsSequence(int argIndex, Context context) {
        ASLObject argument = f.arguments.get(argIndex).evaluate(context);
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
