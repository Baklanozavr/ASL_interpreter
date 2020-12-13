package asl.model.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Тип квалифицированных имен, образуется путём вызова функции qname(x), где x ∈ ASLString
 */
public class QNameAtom extends SyntaxAtom<String> {
    // fixme: what about concurrency?
    /**
     * Кэш созданных объектов, у которых строковое значение подчиняется следующим условиям:
     * <ul>
     * <li>может содержать латинские буквы (a-z, A-Z);</li>
     * <li>может содержать цифры (0-9);</li>
     * <li>может содержать прочерк (_);</li>
     * <li>начинается не с цифры;</li>
     * <li>может содержать двоеточие (:), при этом все подстроки, полученные разделением всей строки по двоеточиям,
     * удовлетворяют всем остальным условиям.</li>
     * </ul>
     * Выполнение данных условий должно контролироваться до вызова соответствующего конструктора.
     */
    protected static final Map<String, QNameAtom> SIMPLE_MEMO = new HashMap<>();
    /**
     * Кэш объектов, созданных путём вызова ASL-функции qname
     */
    protected static final Map<String, QNameAtom> COMPLEX_MEMO = new HashMap<>();

    private final boolean simpleFlag;

    protected QNameAtom(String value, boolean flag) {
        super(value);
        this.simpleFlag = flag;
    }

    public static QNameAtom create(StringAtom string) {
        return COMPLEX_MEMO.computeIfAbsent(string.value(), x -> new QNameAtom(x, false));
    }

    public static QNameAtom create(String value) {
        return SIMPLE_MEMO.computeIfAbsent(value, x -> new QNameAtom(x, true));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QNameAtom qnameAtom = (QNameAtom) o;
        return simpleFlag == qnameAtom.simpleFlag &&
                value.equals(qnameAtom.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, simpleFlag);
    }

    @Override
    public String toDebugString() {
        return "QNameAtom(\"" + value + "\", " + (simpleFlag ? "true" : "false") + ")";
    }
}
