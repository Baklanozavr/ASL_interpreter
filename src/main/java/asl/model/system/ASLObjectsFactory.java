package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.functions.DefinedFunction;
import asl.model.core.functions.DefinedFunctionCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ASLObjectsFactory {

    public static ASLObject makeFunctionCall(String functionName, List<ASLObject> arguments) {
        return DefinedFunctionCaller.call(functionName, arguments)
                .orElseGet(() -> callUserFunction(functionName, arguments));
    }

    private static DefinedFunction callUserFunction(String functionName, List<ASLObject> arguments) {
        return GlobalContext.INSTANCE.getFunction(functionName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown function!"))
                .callOn(arguments);
    }

    public static List<ASLObject> makeSequence(ASLObject... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    private ASLObjectsFactory() {
    }
}
