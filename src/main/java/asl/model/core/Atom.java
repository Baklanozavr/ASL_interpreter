package asl.model.core;

import org.jetbrains.annotations.NotNull;

import static asl.model.core.Undef.UNDEF;

/**
 * Базовый тип для элементов, которые не могут иметь атрибутов
 */
public abstract class Atom extends Thing {

    /**
     * Всегда возвращает UNDEF, так как элементы данного типа не могут иметь аттрибутов
     */
    @Override
    public @NotNull Thing get(Thing attribute) {
        return UNDEF;
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        return lc.setValue(this);
    }

    @Override
    public boolean is(Thing type) {
        return false;
    }
}
