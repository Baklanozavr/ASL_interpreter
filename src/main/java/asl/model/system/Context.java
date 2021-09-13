package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.core.PlainAttributon;
import asl.model.core.Undef;
import asl.model.core.functions.FunctionEvaluator;
import asl.model.core.functions.UserFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static final List<ASLObject> variants = new ArrayList<>();

    public static void putGlobalVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        globalVariables.put(varName, varValue);
    }

    public static @NotNull ASLObject getGlobalVariable(@NotNull String varName) {
        if ("variants".equals(varName)) {
            return SequenceFacade.createSequence(Context.variants);
        }
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

    public Context copy() {
        Context newCopy = new Context(parent);
        newCopy.familyVariables.putAll(familyVariables);
        newCopy.variables.putAll(variables);
        newCopy.value = value;
        newCopy.jump = jump;
        return newCopy;
    }

    public void unite(Context context) {
        if (parent != context.parent) throw new IllegalStateException("Can not unite contexts with different parents!");

        familyVariables.putAll(context.familyVariables);
        variables.putAll(context.variables);
        value = context.value;
        jump = context.jump;
    }

    public ASLObject toASLObject() {
        var variablesObject = new PlainAttributon(variables.size());
        variables.forEach(variablesObject::put);

        var result = new PlainAttributon(3);
        result.put("value", value);
        if (jump != null) {
            result.put("jump", jump.toASLObject());
        }
        result.put("variables", variablesObject);
        return result;
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

    public void clear() {
        if (parent != null) throw new IllegalStateException("This context should not be cleared!");

        globalVariables.clear();
        userFunctions.clear();
        familyVariables.clear();
        variables.clear();
        value = Undef.UNDEF;
        jump = null;
    }
}
