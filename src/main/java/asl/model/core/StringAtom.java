package asl.model.core;

/**
 * ASL-обёртка над Java-строкой
 */
public class StringAtom extends SyntaxAtom<String> {
    public StringAtom(String value) {
        super(value);
    }
}
