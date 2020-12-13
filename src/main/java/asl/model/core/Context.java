package asl.model.core;

import static asl.model.core.Attributes.JUMP;
import static asl.model.core.Attributes.PARENT;
import static asl.model.core.Attributes.VALUE;
import static asl.model.core.Attributes.VARIABLES;

public class Context extends Attributon {
    public Context() {
        this(null);
    }

    public Context(Context parent) {
        put(PARENT, parent);
        put(VARIABLES, new Attributon());
    }

    public Context parent() {
        return (Context) get(PARENT);
    }

    public Attributon variables() {
        return (Attributon) get(VARIABLES);
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
            if (!PARENT.equals(key) && !VARIABLES.equals(key) && !VALUE.equals(key) && !JUMP.equals(key)) {
                result.put(key, value);
            }
        });
    }
}
