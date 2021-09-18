package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import org.jetbrains.annotations.NotNull;

/**
 * Creates a sequence with seqStart equal to one and length equal to count of arguments; <br/>
 * fills the sequence with values of arguments and returns it
 */
public class SeqOne extends FunctionEvaluator {
    public static final String name = "seqOne";

    public SeqOne(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return SequenceFacade.createSequence(evaluateArguments(context));
    }
}
