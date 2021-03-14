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
        return Optional.ofNullable(FUNCTIONS.get(functionName))
                .orElseThrow(() -> new IllegalArgumentException("Unknown function!"))
                .createFunction(arguments);
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
