package asl.model.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Элементы подтипа Sequence типа Attributon называются последовательностями.<p>
 * Они определяют конечные последовательности вещей и характеризуются атрибутами first и rest.<p>
 * Если tg – последовательность длины n, то av(tg, first) – первый элемент tg, av(tg, rest, first) – второй элемент tg,
 * av(g, rest, rest, first) – третий элемент tg и т. д
 * <p>
 * Может иметь дополнительные атрибуты.
 */
public class Sequence extends Attributon {
    private final List<Thing> seq;

    public Sequence(Thing... elements) {
        seq = new ArrayList<>();
        Collections.addAll(seq, elements);
    }

    private Sequence(final @NotNull List<Thing> elements) {
        seq = elements;
    }

    public void add(Thing element) {
        seq.add(element);
    }

    @Override
    public Thing get(Thing attribute) {
        if (attribute instanceof QNameAtom) {
            switch (((QNameAtom) attribute).value()) {
                case "first":
                    return first();
                case "rest":
                    return rest();
            }
        }
        return super.get(attribute);
    }

    public Thing first() {
        return seq.isEmpty() ? null : seq.get(0);
    }

    public Sequence rest() {
        int size = seq.size();
        return size > 0 ? new Sequence(seq.subList(1, size)) : null;
    }

    private boolean listsAreEqual(List<Thing> left, List<Thing> right) {
        if (left.size() != right.size())
            return false;
        for (int i = 0; i < left.size(); ++i)
            if (!Objects.equals(left.get(i), right.get(i)))
                return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && listsAreEqual(seq, ((Sequence) o).seq);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Arrays.deepHashCode(seq.toArray(Thing[]::new));
    }

    @Override
    public String toString() {
        return "(" + seq.stream().map(Thing::serialize).collect(Collectors.joining(", ")) + ")";
    }
}
