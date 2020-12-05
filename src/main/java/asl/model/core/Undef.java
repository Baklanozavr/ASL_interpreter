package asl.model.core;

import org.jetbrains.annotations.Nullable;

public final class Undef extends Thing {
    public static final Undef INSTANCE = new Undef();

    private Undef() {
    }

    @Override
    public String toString() {
        return "undef";
    }

    @Override
    public @Nullable Thing get(Thing attribute) {
        return null;
    }
}
