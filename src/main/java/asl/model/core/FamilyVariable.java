package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

public class FamilyVariable extends ASLVariable {
    public FamilyVariable(String name) {
        super(name);
    }

    private FamilyVariable(FamilyVariable v) {
        super(v.name, v.attributes);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return context.getFamilyVariable(name).evaluate(context);
    }

    @Override
    public void setToContext(@NotNull Context context, @NotNull ASLObject value) {
        context.putFamilyVariable(name, value);
    }

    @Override
    public @NotNull ASLObject clone() {
        return new FamilyVariable(this);
    }

    @Override
    public @NotNull String toString() {
        return "$@" + name;
    }
}
