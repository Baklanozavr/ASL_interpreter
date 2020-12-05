package asl.model.core;

/**
 * Переменная с именем name
 */
public class ASLVariable extends Attributon {
    public ASLVariable(QNameAtom name) {
        setType(Types.VARIABLE);
//        attributes.put(ASLQName.NAME, name);
    }
}
