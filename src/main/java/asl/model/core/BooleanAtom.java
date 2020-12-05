package asl.model.core;

/**
 * Аналог boolean значения, но строка.
 * Может иметь только одно из двух значений: "true" или "false"
 */
public class BooleanAtom extends QNameAtom {
    public static final BooleanAtom TRUE = new BooleanAtom("true");
    public static final BooleanAtom FALSE = new BooleanAtom("false");

    private BooleanAtom(String value) {
        super(value, true);
        SIMPLE_MEMO.put(value, this);
    }

    @Override
    public String toString() {
        return "BooleanAtom(" + value() + ")";
    }
}
