package asl.model.system;

import asl.model.core.ASLObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ASLObjectsFactory {
    private static final Map<String, FunctionCallEnum> FUNCTIONS = Arrays.stream(FunctionCallEnum.values())
            .collect(Collectors.toUnmodifiableMap(FunctionCallEnum::functionName, Function.identity()));

    private ASLObjectsFactory() {
    }

    public static ASLObject makeFunctionCall(String functionName, List<ASLObject> arguments) {
        FunctionCallEnum systemFunction = FUNCTIONS.get(functionName);
        if (systemFunction != null)
            return systemFunction.createFunction(arguments);

        return GlobalContext.INSTANCE.getFunction(functionName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown function!"))
                .callOn(arguments);
    }

    public static ASLObject makeFunctionCall(String functionName, ASLObject... arguments) {
        return Optional.ofNullable(FUNCTIONS.get(functionName))
                .orElseThrow(() -> new IllegalArgumentException("Unknown function!"))
                .createFunction(Arrays.asList(arguments));
    }

    public static List<ASLObject> makeSequence(ASLObject... args) {
        return new ArrayList<>(Arrays.asList(args));
    }
}
