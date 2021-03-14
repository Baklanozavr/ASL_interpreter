package asl.model.core;

import asl.model.system.Context;

public abstract class ASLVariable extends ASLObjectWithAttributes {
    protected final String name;

    protected ASLVariable(String name) {
        this.name = name;
    }

    abstract public void setToContext(Context context, ASLObject value);

    public String name() {
        return name;
    }
}
