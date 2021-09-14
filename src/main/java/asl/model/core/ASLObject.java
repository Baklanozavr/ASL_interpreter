package asl.model.core;

import asl.model.system.BranchController;
import asl.model.system.Context;
import asl.model.system.StopSignal;
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

    public final void evaluateToContext(final Context context) {
        Context currentContext;
        do {
            currentContext = context.copy();
            try {
                final ASLObject result = evaluate(currentContext);
                currentContext.setValue(result);
            } catch (Jump jump) {
                currentContext.setJump(jump);
            } catch (StopSignal ignore) {
                currentContext = null;
            }

            if (currentContext != null && !BranchController.isEmpty()) {
                Context.variants.add(currentContext.toASLObject());
            }
        } while (BranchController.shiftNext());

        if (currentContext != null) {
            context.unite(currentContext);
            Jump jump = context.getJump();
            if (jump != null) throw jump;
        }
    }

    /** Creates shallow copy of ASL object (new object with the same fields/attributes) */
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
