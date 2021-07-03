package asl.model.core;

import asl.model.system.Context;

import java.util.Map;

public abstract class ASLVariable extends ASLObjectWithAttributes {
    protected final String name;

    protected ASLVariable(String name) {
        this.name = name;
    }

    protected ASLVariable(String name, Map<ASLObject, ASLObject> attributes) {
        super(attributes);
        this.name = name;
    }

    abstract public void setToContext(Context context, ASLObject value);

    public String name() {
        return name;
    }
}
