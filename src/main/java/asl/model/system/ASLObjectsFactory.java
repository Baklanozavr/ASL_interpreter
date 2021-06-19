package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.functions.DefinedFunction;
import asl.model.core.functions.DefinedFunctionCaller;
import asl.model.core.functions.UserFunction;

import java.util.List;

public final class ASLObjectsFactory {

    public static DefinedFunction makeFunctionCall(String functionName, List<ASLObject> arguments) {
        return DefinedFunctionCaller.call(functionName, arguments)
                .orElseGet(() -> new UserFunction(functionName, arguments));
    }

    private ASLObjectsFactory() {
    }
}
