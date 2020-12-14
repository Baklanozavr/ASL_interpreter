package asl.model.core.functions;

import asl.model.core.Attributon;
import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.IntegerAtom;
import asl.model.core.Thing;
import org.jetbrains.annotations.NotNull;

import static asl.model.core.Undef.UNDEF;

public abstract class AbstractFunction extends Attributon {
    @Override
    public @NotNull Thing get(Thing attribute) {
        return attribute instanceof IntegerAtom
                ? getFunction(((IntegerAtom) attribute).value())
                : UNDEF;
    }

    @Override
    abstract public @NotNull Context eval(Context lc, GlobalContext gc);

    abstract protected @NotNull Thing getFunction(int argumentsNumber);
}
