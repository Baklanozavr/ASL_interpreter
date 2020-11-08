package asl.model.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Тип квалифицированных имен, образуется путём вызова функции qname(x), где x ∈ ASLString
 */
public class ASLQName extends SyntaxAtom<String> {
    // fixme: what about concurrency?
    protected static final Map<String, ASLQName> MEMO = new HashMap<>();

    private final String value;

    protected ASLQName(String value) {
        this.value = value;
    }

    public static ASLQName create(ASLString string) {
        return create(string.value());
    }

    public static ASLQName create(String value) {
        return MEMO.computeIfAbsent(value, ASLQName::new);
    }

    @Override
    public String value() {
        return value;
    }
}
