package asl.model.core;

import asl.model.util.AttributonHelper;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import static asl.model.core.Undef.UNDEF;

/** Base type for all elements with attributes */
public abstract class ASLObjectWithAttributes extends ASLObject {
    public final Map<Attribute, ASLObject> attributes;

    protected ASLObjectWithAttributes() {
        attributes = new HashMap<>();
    }

    protected ASLObjectWithAttributes(Map<Attribute, ASLObject> attributes) {
        this.attributes = attributes;
    }

    public Map<ASLObject, ASLObject> getAttributes() {
        IdentityHashMap<ASLObject, ASLObject> resultMap = new IdentityHashMap<>();
        attributes.forEach((k, v) -> resultMap.put(k.key, v));
        return resultMap;
    }

    @NotNull
    public ASLObject get(@NotNull ASLObject attrKey) {
        return attributes.getOrDefault(new Attribute(attrKey), Undef.UNDEF);
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
            attributes.remove(new Attribute(attrKey));
        } else {
            attributes.put(new Attribute(attrKey), attrValue);
        }
        return this;
    }

    @Override
    public final boolean equalsLink(ASLObject obj) {
        return this == obj;
    }

    protected boolean attrsEqualsShallow(ASLObjectWithAttributes o) {
        return attributes.equals(o.attributes);
    }

    protected boolean attrsEqualsDeep(ASLObjectWithAttributes o) {
        return new AttributonHelper(this).hasSameStructureWith(o);
    }

    protected Map<Attribute, ASLObject> attrsCopyShallow() {
        return new HashMap<>(attributes);
    }

    @NotNull
    protected Map<Attribute, ASLObject> emptyAttrsMap() {
        return new HashMap<>(attributes.size());
    }

    public abstract ASLObjectWithAttributes copyDeepWithoutAttributes();

    @Override
    public final @NotNull ASLObject copyDeep() {
        return new AttributonHelper(this).getDeepCopy();
    }
}
