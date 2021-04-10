package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoadDataFunction extends FileDataFunction {
    public LoadDataFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.LOAD_DATA, arguments);
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
