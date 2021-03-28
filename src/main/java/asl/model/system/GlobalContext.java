package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.Variable;
import asl.model.core.functions.PrognFunction;
import asl.model.core.functions.UserFunction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p> value, tvalue, jvalue, jtype, icontext, lcontext ∈ Cα;
 * <p> gcontext, actual, arguments, body, special, varied ∈ Cβ;
 */
public final class GlobalContext {
    public static final GlobalContext INSTANCE = new GlobalContext();

    private final Map<String, UserFunction.Caller> userFunctions = new HashMap<>();
    private final Map<String, ASLObject> variables = new HashMap<>();

    private GlobalContext() {
    }

    public void addFunction(boolean isSpecial,
                            boolean isVaried,
                            String name,
                            PrognFunction body,
                            List<Variable> localVariables) {
        userFunctions.put(name, UserFunction.createCaller(isSpecial, isVaried, name, body, localVariables));
    }

    public Optional<UserFunction.Caller> getFunction(@NotNull String name) {
        return Optional.ofNullable(userFunctions.get(name));
    }
}
