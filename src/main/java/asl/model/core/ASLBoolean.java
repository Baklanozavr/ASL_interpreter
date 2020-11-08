package asl.model.core;

/**
 * Аналог boolean значения, но строка.
 * Может иметь только одно из двух значений: "true" или "false"
 */
public class ASLBoolean extends ASLQName {
    public static final ASLBoolean TRUE = new ASLBoolean("true");
    public static final ASLBoolean FALSE = new ASLBoolean("false");

    private ASLBoolean(String value) {
        super(value);
        MEMO.put(value, this);
    }
}
