package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GlobalVariable extends ASLVariable {
    public GlobalVariable(String name) {
        super(name);
    }

    private GlobalVariable(String name, Map<ASLObject, ASLObject> attributes) {
        super(name, attributes);
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
    public @NotNull GlobalVariable copyShallow() {
        return new GlobalVariable(name, attrsCopyShallow());
    }

    @Override
    public @NotNull GlobalVariable copyDeep() {
        return new GlobalVariable(name, attrsCopyDeep());
    }

    @Override
    public @NotNull String toString() {
        return '@' + name;
    }
}
