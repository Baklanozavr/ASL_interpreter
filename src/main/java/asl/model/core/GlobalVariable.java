package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public class GlobalVariable extends ASLVariable {
    public GlobalVariable(String name) {
        super(name);
    }

    private GlobalVariable(GlobalVariable v) {
        super(v.name, v.attributes);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return Context.getGlobalVariable(name).evaluate(context);
    }

    @Override
    public void setToContext(@NotNull Context context, @NotNull ASLObject value) {
        Context.putGlobalVariable(name, value);
    }

    @Override
    public @NotNull ASLObject clone() {
        return new GlobalVariable(this);
    }

    @Override
    public @NotNull String toString() {
        return '@' + name;
    }
}
