package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class ASLVariable extends ASLObjectWithAttributes {
    protected final String name;

    protected ASLVariable(@NotNull String name) {
        this.name = name;
    }

    protected ASLVariable(@NotNull String name, @NotNull Map<ASLObject, ASLObject> attributes) {
        super(attributes);
        this.name = name;
    }

    abstract public void setToContext(@NotNull Context context, @NotNull ASLObject value);

    public @NotNull String name() {
        return name;
    }
}
