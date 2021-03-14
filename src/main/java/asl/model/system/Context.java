package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.Undef;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A container object which could be passed to {@link ASLObject#evaluate}
 */
public final class Context {
    private final Map<String, ASLObject> variables = new HashMap<>();
    private final Map<String, ASLObject> attrVariables = new HashMap<>();
    private final Context parent;
    private ASLObject value;

    public Context(Context parent) {
        this.parent = parent;
    }

    public void putVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        variables.put(varName, varValue);
    }

    public ASLObject getVariable(@NotNull String varName) {
        return variables.getOrDefault(varName, Undef.UNDEF);
    }

    public void putAttrVariable(@NotNull String varName, @NotNull ASLObject varValue) {
        attrVariables.put(varName, varValue);
    }

    public ASLObject getAttrVariable(@NotNull String varName) {
        return attrVariables.getOrDefault(varName, Undef.UNDEF);
    }

    public Context parent() {
        return parent;
    }

    public ASLObject value() {
        return value;
    }

    public <T extends ASLObject> Context setValue(T value) {
        this.value = value;
        return this;
    }
}
