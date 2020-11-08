package asl.model.core;

/**
 * Базовый тип для элементов, которые не могут иметь атрибутов
 */
public abstract class Atom extends Thing {

    /**
     * Всегда возвращает null, так как элементы данного типа не могут иметь аттрибутов
     */
    @Override
    public Thing get(Thing attribute) {
        return null;
    }

    @Override
    public boolean is(Thing type) {
        return false;
    }
}
