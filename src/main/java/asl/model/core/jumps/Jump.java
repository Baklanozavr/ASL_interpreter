package asl.model.core.jumps;

import asl.model.core.Attributon;
import asl.model.core.QNameAtom;

public abstract class Jump extends Attributon {
    public Jump(QNameAtom type) {
        super();
        setType(type);
    }
}
