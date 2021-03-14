package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.CommonAttributes.SETQ_JUMP;

public final class AttrVariable extends ASLVariable {
    public AttrVariable(String name) {
        super(name);
    }

    @Override
    public void setToContext(Context context, ASLObject value) {
        if (value instanceof Atom)
            throw new Jump(SETQ_JUMP);

        context.putAttrVariable(name, value);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return context.getAttrVariable(name);
    }

    @Override
    public String toString() {
        return '#' + name;
    }
}
