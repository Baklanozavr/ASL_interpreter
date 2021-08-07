package asl.model.core;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static asl.model.core.Undef.UNDEF;

/** Base type for all elements with attributes */
public abstract class ASLObjectWithAttributes extends ASLObject {
    public final Map<ASLObject, ASLObject> attributes;

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

    @NotNull
    public ASLObjectWithAttributes put(int i, @NotNull ASLObject attrValue) {
        return put(IntegerAtom.of(i), attrValue);
    }

    /**
     * Associates the specified value with the specified attribute in this ASL object
     * If the object previously contained a mapping for the attribute, the old value is replaced by the specified value.
     *
     * @param attrKey   attribute to be a key (could be {@link Undef#UNDEF})
     * @param attrValue value to be associated with the specified attribute
     * @return {@code this} object
     */
    @NotNull
    public ASLObjectWithAttributes put(@NotNull ASLObject attrKey, @NotNull ASLObject attrValue) {
        if (UNDEF.equals(attrValue)) {
            attributes.remove(attrKey);
        } else {
            attributes.put(attrKey, attrValue);
        }
        return this;
    }

    @Override
    public final boolean equalsLink(ASLObject obj) {
        return this == obj;
    }

    protected boolean attrsEqualsShallow(Map<ASLObject, ASLObject> attrs) {
        return attributes.equals(attrs);
    }

    protected boolean attrsEqualsDeep(Map<ASLObject, ASLObject> attrs) {
        // todo: implement
        return false;
    }

    protected Map<ASLObject, ASLObject> attrsCopyShallow() {
        return new HashMap<>(attributes);
    }

    protected Map<ASLObject, ASLObject> attrsCopyDeep() {
        // todo: implement
        return new HashMap<>(attributes);
    }
}
