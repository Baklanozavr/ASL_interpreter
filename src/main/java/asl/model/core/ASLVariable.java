package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Predicate;

public abstract class ASLVariable extends ASLObjectWithAttributes {
    protected final String name;

    protected ASLVariable(@NotNull String name) {
        this.name = name;
    }

    protected ASLVariable(@NotNull String name, @NotNull Map<Attribute, ASLObject> attributes) {
        super(attributes);
        this.name = name;
    }

    abstract public void setToContext(@NotNull Context context, @NotNull ASLObject value);

    public @NotNull String name() {
        return name;
    }

    @Override
    public final boolean equalsShallow(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsShallow);
    }

    @Override
    public final boolean equalsDeep(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsDeep);
    }

    private boolean isEqualTo(ASLObject o, Predicate<ASLObjectWithAttributes> compareFunction) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASLVariable oVariable = (ASLVariable) o;
        return name.equals(oVariable.name) && compareFunction.test(oVariable);
    }
}
