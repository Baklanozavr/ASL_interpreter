package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public final class Variable extends ASLVariable {
    public Variable(String name) {
        super(name);
    }

    @Override
    public void setToContext(Context context, ASLObject value) {
        context.putVariable(name, value);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return context.getVariable(name);
    }

    @Override
    public String toString() {
        return '$' + name;
    }
}
