package asl.model.core.jumps;

import asl.model.core.Attributes;
import asl.model.core.Undef;

public class ArefJump extends Jump {
    public ArefJump() {
        super(Attributes.AREF_JUMP);
        setType(Undef.UNDEF);
    }
}
