package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FamilyVariable extends ASLVariable {
    public FamilyVariable(String name) {
        super(name);
    }

    private FamilyVariable(String name, Map<Attribute, ASLObject> attributes) {
        super(name, attributes);
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
    public @NotNull FamilyVariable copyShallow() {
        return new FamilyVariable(name, attrsCopyShallow());
    }

    @Override
    public FamilyVariable copyDeepWithoutAttributes() {
        return new FamilyVariable(name, emptyAttrsMap());
    }

    @Override
    public @NotNull String toString() {
        return "$@" + name;
    }
}
