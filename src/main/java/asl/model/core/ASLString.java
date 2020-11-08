package asl.model.core;

import java.util.Objects;

/**
 * ASL-обёртка над Java-строкой
 */
public class ASLString extends SyntaxAtom<String> {
    private final String value;

    public ASLString(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASLString aslString = (ASLString) o;
        return value.equals(aslString.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
