package asl.model.core;

public final class Jump extends RuntimeException {
    private final ASLObject type;
    private final ASLObject value;

    public Jump(ASLObject type, ASLObject value) {
        super(null, null, false, false);
        this.type = type;
        this.value = value;
    }

    public Jump(ASLObject type) {
        this(type, null);
    }

    public boolean is(ASLObject type) {
        return this.type.equals(type);
    }
}
