package asl.model.core;

import org.jetbrains.annotations.NotNull;

/**
 * Базовый супер-тип, которому принадлежат все элементы языка ASL.
 */
abstract public class Thing {

    /**
     * Аналог ф-ции s(u1, u2) - элемент u1 имеет атрибут u2, если u1.get(u2) ≠ null.
     */
    abstract public @NotNull Thing get(Thing attribute);

    /**
     * Перегрузка для java String для удобства работы с методом
     */
    public @NotNull Thing get(String attributeName) {
        return get(QNameAtom.create(attributeName));
    }

    /**
     * Перегрузка для java int для удобства работы с методом
     */
    public @NotNull Thing get(int index) {
        return get(new IntegerAtom(index));
    }

    /**
     * Аналог ф-ции eval(e, lc, gc) - выражение e является элементом, от которого вызывается данный метод.
     * <ul> Вычисление выражений:
     * <li> порождает новые локальные контексты вычислений [docs 1.4]
     * <li> может возвращать джампы,
     * которые определяются специальным атрибутом jump [docs 1.5]
     * <li> может возвращать значения,
     * которые определяются специальным атрибутом value [docs 1.7]
     * </ul>
     * @param lc локальный контекст, в котором будет происходить вычисление
     * @param gc глобальный контекст, содержащий функции, доступные во время вычисления
     */
    abstract public @NotNull Context eval(Context lc, GlobalContext gc);

    /**
     * Любой элемент типа Thing может рассматриваться как атрибутный тип.
     * Множество элементов атрибутного типа u определяется через атрибут u следующим образом:
     * u1 ∈ u тогда и только тогда, когда av(u1, u) = true (в текущей реализации u1.get(u) = true)
     */
    public boolean is(Thing type) {
        return BooleanAtom.TRUE.equals(get(type));
    }

    public boolean isUndef() {
        return equals(Undef.UNDEF);
    }

    public boolean isNotUndef() {
        return !isUndef();
    }
}
