package asl.model.core;

import static asl.model.core.Attributes.*;

public class Context extends Attributon {
    public Context() {
        this(null);
    }

    public Context(Context parent) {
        this(parent, new Attributon());
    }

    public Context(Context parent, Attributon variables) {
        put("parent", parent);
        put(VARIABLES, variables);
        put("attributonVariables", new Attributon());
        put(VALUE, Undef.UNDEF);
        put(JUMP, Undef.UNDEF);
    }

    public Context getChild() {
        return new Context(this);
    }

    public Context parent() {
        return (Context) get(QNameAtom.create("parent"));
    }

    public Attributon variables() {
        return (Attributon) get(VARIABLES);
    }

    public Attributon attributonVariables() {
        return (Attributon) get(QNameAtom.create("attributonVariables"));
    }

    public Thing value() {
        return get(VALUE);
    }

    public Thing jump() {
        return get(JUMP);
    }

    public Context setValue(Thing value) {
        return (Context) put(VALUE, value);
    }

    public Context setJump(Thing jump) {
        return (Context) put(JUMP, jump);
    }

    /**
     * Присваивает атрибутам в контексте {@code result}, отличным от parent, jump, value, variables и attributonVariable,
     * значения соответствующих атрибутов текущего контекста
     */
    public void returnTo(Context result) {
        attributes.forEach((key, value) -> {
            if (QNameAtom.create("parent").equals(key) ||
                    VARIABLES.equals(key) ||
                    QNameAtom.create("attributonVariables").equals(key) ||
                    VALUE.equals(key) ||
                    JUMP.equals(key)) {
                return;
            }
            result.put(key, value);
        });
    }
}
