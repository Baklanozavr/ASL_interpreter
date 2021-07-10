package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public final class LocalVariable extends ASLVariable {
    public LocalVariable(String name) {
        super(name);
    }

    private LocalVariable(LocalVariable v) {
        super(v.name, v.attributes);
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
    public @NotNull LocalVariable clone() {
        return new LocalVariable(this);
    }

    @Override
    public @NotNull String toString() {
        return '$' + name;
    }
}
