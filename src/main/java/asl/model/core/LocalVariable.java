package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class LocalVariable extends ASLVariable {
    public LocalVariable(String name) {
        super(name);
    }

    private LocalVariable(String name, Map<Attribute, ASLObject> attributes) {
        super(name, attributes);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return context.getLocalVariable(name);
    }

    @Override
    public void setToContext(@NotNull Context context, @NotNull ASLObject value) {
        context.putLocalVariable(name, value);
    }

    @Override
    public @NotNull LocalVariable copyShallow() {
        return new LocalVariable(name, attrsCopyShallow());
    }

    @Override
    public @NotNull LocalVariable copyDeep() {
        return new LocalVariable(name, attrsCopyDeep());
    }

    @Override
    public @NotNull String toString() {
        return '$' + name;
    }
}
