package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * ASL representation for nothing - {@code null} and {@code void} analog. Singleton.
 */
public final class Undef extends ASLObject {
    public static final Undef UNDEF = new Undef();

    private Undef() {
    }

    @Override
    public @NotNull String toString() {
        return "undef";
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return this;
    }

    @Override
    public @NotNull Undef copyShallow() {
        return this;
    }

    @Override
    public @NotNull Undef copyDeep() {
        return this;
    }

    @Override
    public boolean equalsShallow(ASLObject obj) {
        return obj == UNDEF;
    }

    @Override
    public boolean equalsDeep(ASLObject obj) {
        return obj == UNDEF;
    }

    @Override
    public boolean equalsLink(ASLObject obj) {
        return obj == UNDEF;
    }
}
