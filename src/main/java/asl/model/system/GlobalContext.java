package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Variable;
import asl.model.core.functions.FunctionEvaluator;
import asl.model.core.functions.Progn;
import asl.model.core.functions.UserFunction;

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

    private final Map<String, UserFunction> userFunctions = new HashMap<>();
    private final Map<String, ASLObject> variables = new HashMap<>();

    private GlobalContext() {
    }

    public void addUserFunction(boolean isSpecial,
                                boolean isVaried,
                                String name,
                                Progn body,
                                List<Variable> localVariables) {
        userFunctions.put(name, new UserFunction(isSpecial, isVaried, body, localVariables));
    }

    public Optional<FunctionEvaluator> getUserFunction(FunctionCall f) {
        return Optional.ofNullable(userFunctions.get(f.name)).map(fun -> fun.createEvaluator(f));
    }
}
