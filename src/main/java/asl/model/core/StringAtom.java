package asl.model.core;

/**
 * ASL-обёртка над Java-строкой
 */
public class StringAtom extends Atom<String> {
    public StringAtom(String value) {
        super(value);
    }
}
