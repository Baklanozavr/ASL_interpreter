package asl.model.core;

import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.AttributonHelper;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Ordinary object with attributes.
 * Any element of this type could be treated as a sequence (see {@link asl.model.system.SequenceFacade})
 */
public class PlainAttributon extends ASLObjectWithAttributes {
    public PlainAttributon() {
    }

    public PlainAttributon(int size) {
        super(new HashMap<>(size));
    }

    private PlainAttributon(Map<Attribute, ASLObject> attributes) {
        super(attributes);
    }

    public PlainAttributon put(@NotNull String attrName, ASLObject attrValue) {
        return (PlainAttributon) put(QNameAtom.create(attrName), attrValue);
    }

    public PlainAttributon setType(@NotNull ASLObject type) {
        return (PlainAttributon) put(type, BooleanAtom.TRUE);
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    /**
     * From Map's javadoc: "great care must be exercised if mutable objects are used as map keys..."
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public @NotNull String toString() {
        Sequence sequenceCandidate = SequenceFacade.toSequence(this);
        return sequenceCandidate != null ?
                sequenceCandidate.toString() :
                new AttributonHelper(this).getString();
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return this;
    }

    @Override
    public @NotNull PlainAttributon copyShallow() {
        return new PlainAttributon(attrsCopyShallow());
    }

    @Override
    public @NotNull PlainAttributon copyDeep() {
        return new PlainAttributon(attrsCopyDeep());
    }

    @Override
    public boolean equalsShallow(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsShallow);
    }

    @Override
    public boolean equalsDeep(ASLObject o) {
        return isEqualTo(o, this::attrsEqualsDeep);
    }

    private boolean isEqualTo(ASLObject o, Predicate<Map<Attribute, ASLObject>> compareFunction) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainAttributon attributon = (PlainAttributon) o;
        return compareFunction.test(attributon.attributes);
    }
}
