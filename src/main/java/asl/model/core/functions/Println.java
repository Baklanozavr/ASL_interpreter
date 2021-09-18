package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class Println extends FunctionEvaluator {
    public static final String name = "println";

    public Println(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        String resultString = evaluateArguments(context)
                .stream()
                .map(ASLObject::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(resultString);
        return Undef.UNDEF;
    }
}
