package asl.model.core;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static asl.model.core.Undef.UNDEF;

public abstract class ASLObjectWithAttributes extends ASLObject {
    protected final Map<ASLObject, ASLObject> attributes;

    protected ASLObjectWithAttributes() {
        attributes = new HashMap<>();
    }

    protected ASLObjectWithAttributes(Map<ASLObject, ASLObject> attributes) {
        this.attributes = attributes;
    }

    @NotNull
    public ASLObject get(@NotNull ASLObject attrKey) {
        return attributes.getOrDefault(attrKey, Undef.UNDEF);
    }

    @NotNull
    public ASLObject get(int i) {
        return get(IntegerAtom.of(i));
    }

    /**
     * Associates the specified value with the specified attribute in this ASL object
     * If the object previously contained a mapping for the attribute, the old value is replaced by the specified value.
     *
     * @param attrKey   attribute to be a key (could be {@link Undef#UNDEF})
     * @param attrValue value to be associated with the specified attribute
     * @return {@code this} object
     */
    public ASLObjectWithAttributes put(@NotNull ASLObject attrKey, @NotNull ASLObject attrValue) {
        if (UNDEF.equals(attrValue)) {
            attributes.remove(attrKey);
        } else {
            attributes.put(attrKey, attrValue);
        }
        return this;
    }
}
