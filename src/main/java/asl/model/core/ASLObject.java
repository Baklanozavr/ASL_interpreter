package asl.model.core;

import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/** Base ancestor for all ASL objects like {@link java.lang.Object} in Java */
public abstract class ASLObject {

    /**
     * Аналог ф-ции eval(e, lc, gc) - выражение e является элементом, от которого вызывается данный метод.
     * <ul> Вычисление выражений:
     * <li> порождает новые локальные контексты вычислений [docs 1.4]
     * <li> может возвращать джампы,
     * которые определяются специальным атрибутом jump [docs 1.5]
     * <li> может возвращать значения,
     * которые определяются специальным атрибутом value [docs 1.7]
     * </ul>
     */
    @NotNull
    public abstract ASLObject evaluate(Context context);

    @NotNull
    public final ASLObject evaluateToContext(Context context) {
        ASLObject result = evaluate(context);
        context.setValue(result);
        return result;
    }

    /**
     * Creates shallow copy of ASL object (new object with the same fields/attributes).
     */
    abstract public @NotNull ASLObject copyShallow();

    /** Creates deep copy of ASL object (new object with the fully copied fields/attributes) */
    abstract public @NotNull ASLObject copyDeep();

    /** Comparison of attributes */
    abstract public boolean equalsShallow(ASLObject o);

    /** Deep comparison */
    abstract public boolean equalsDeep(ASLObject o);

    /** Comparison of links, like == */
    abstract public boolean equalsLink(ASLObject o);

    @Override
    abstract public @NotNull String toString();
}
