package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import asl.model.system.StopSignal;
import org.jetbrains.annotations.NotNull;

public class Stop extends FunctionEvaluator {
    public static final String name = "stop";

    public Stop(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        throw new StopSignal();
    }
}
