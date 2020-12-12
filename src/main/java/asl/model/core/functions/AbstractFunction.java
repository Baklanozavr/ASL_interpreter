package asl.model.core.functions;

import asl.model.core.Attributes;
import asl.model.core.Attributon;
import asl.model.core.QNameAtom;

public abstract class AbstractFunction extends Attributon {
    AbstractFunction(QNameAtom name) {
        super();
        put(Attributes.NAME, name);
    }
}
