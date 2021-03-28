package asl.model.core;

import org.jetbrains.annotations.NotNull;

public class Jump extends RuntimeException {
    @NotNull
    private final ASLObject type;
    private final ASLObject value;

    public Jump(@NotNull ASLObject type, ASLObject value) {
        super(null, null, false, false);
        this.type = type;
        this.value = value;
    }

    public Jump(@NotNull ASLObject type, String value) {
        this(type, QNameAtom.create(value));
    }

    public Jump(ASLObject type) {
        this(type, Undef.UNDEF);
    }

    public boolean is(ASLObject type) {
        return this.type.equals(type);
    }

    @Override
    public String toString() {
        return "JUMP!\ntype: " + type.toString() + (value == null ? "" : "\nvalue: " + value.toString());
    }
}
