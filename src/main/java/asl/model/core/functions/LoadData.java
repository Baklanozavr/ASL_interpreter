package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoadData extends FileDataFunctionEvaluator {
    public static final String name = "loadData";

    public LoadData(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        String fileName = evalArgAsFileName(context);
        List<ASLObject> aslExpressions = readExpressionsFromFile(fileName);
        return aslExpressions.isEmpty() ?
                Undef.UNDEF :
                aslExpressions.get(aslExpressions.size() - 1);
    }
}
