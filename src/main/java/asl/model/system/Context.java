package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.core.functions.FunctionEvaluator;
import asl.model.core.functions.UserFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A container object which collect all data about current program status:
 * <ul>
 *     <li> variables (local, global and context connected) </li>
 *     <li> created functions </li>
 *     <li> current value </li>
 *     <li> current jump (exception) </li>
 * </ul>
 */
public final class Context {
    public static final Map<String, ASLObject> globalVariables = new ConcurrentHashMap<>();
    public static final Map<String, UserFunction> userFunctions = new ConcurrentHashMap<>();

    public static void putGlobalVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        globalVariables.put(varName, varValue);
    }

    public static @NotNull ASLObject getGlobalVariable(@NotNull String varName) {
        return globalVariables.getOrDefault(varName, Undef.UNDEF);
    }

    public static void addUserFunction(@NotNull String name, @NotNull UserFunction userFunction) {
        userFunctions.put(name, userFunction);
    }

    public static Optional<FunctionEvaluator> getUserFunction(FunctionCall f) {
        return Optional.ofNullable(userFunctions.get(f.name)).map(fun -> fun.createEvaluator(f));
    }

    public static Context empty() {
        return new Context(null);
    }

    private final Map<String, ASLObject> familyVariables;
    private final Map<String, ASLObject> variables = new HashMap<>();
    private final Context parent;
    private ASLObject value = Undef.UNDEF;
    private Jump jump;

    public Context(@Nullable Context parent) {
        this.parent = parent;
        familyVariables = parent == null ? new ConcurrentHashMap<>() : parent.familyVariables;
    }

    public void putLocalVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        variables.put(varName, varValue);
    }

    public @NotNull ASLObject getLocalVariable(@NotNull String varName) {
        return variables.getOrDefault(varName, Undef.UNDEF);
    }

    public void putFamilyVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        globalVariables.put(varName, varValue);
    }

    public @NotNull ASLObject getFamilyVariable(@NotNull String varName) {
        return globalVariables.getOrDefault(varName, Undef.UNDEF);
    }

    public @NotNull ASLObject value() {
        return value;
    }

    public <T extends ASLObject> @NotNull Context setValue(@NotNull T value) {
        this.value = value;
        return this;
    }

    public @Nullable Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}
