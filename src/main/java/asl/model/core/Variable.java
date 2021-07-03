package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public final class Variable extends ASLVariable {
    public Variable(String name) {
        super(name);
    }

    private Variable(Variable v) {
        super(v.name, v.attributes);
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
    public @NotNull Variable clone() {
        return new Variable(this);
    }

    @Override
    public @NotNull String toString() {
        return '$' + name;
    }
}
