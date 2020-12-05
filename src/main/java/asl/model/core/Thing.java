package asl.model.core;

import org.jetbrains.annotations.Nullable;

/**
 * Базовый супер-тип, которому принадлежат все элементы языка ASL.
 */
abstract public class Thing {

    /**
     * Аналог ф-ции av(u1, u2) - элемент u1 имеет атрибут u2, если u1.get(u2) ≠ null.
     * Функция av семантическая, не входит в синтаксис языка.
     */
    abstract public @Nullable Thing get(Thing attribute);

    /**
     * Любой элемент типа Thing может рассматриваться как атрибутный тип.
     * Множество элементов атрибутного типа u определяется через атрибут u следующим образом:
     * u1 ∈ u тогда и только тогда, когда av(u1, u) = true (в текущей реализации u1.get(u) = true)
     */
    public boolean is(Thing type) {
        return BooleanAtom.TRUE.equals(get(type));
    }

    public static String serialize(Thing thing) {
        return thing == null ? "undef" : thing.toString();
    }
}
