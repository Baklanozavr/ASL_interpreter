package asl.model.core;

import org.jetbrains.annotations.NotNull;

public final class Undef extends Thing {
    public static final Undef UNDEF = new Undef();

    private Undef() {
    }

    @Override
    public String toString() {
        return "undef";
    }

    @Override
    public @NotNull Thing get(Thing attribute) {
        return UNDEF;
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        return lc.setValue(UNDEF);
    }
}
