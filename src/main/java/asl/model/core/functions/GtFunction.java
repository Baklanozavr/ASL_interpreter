package asl.model.core.functions;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция gt имеет аргументы (x1, …, xn). Пусть > - бинарная операция «больше» на числах или строках.
 * Функция gt определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если n ≤ 1, то возвратить значение true.
 * <li> Если n > 1, то
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 > uj для 1 < j < i < n, и неверно, что ui-1 > ui,
 * то не вычислять xi+1, …, xn и возвратить значение false.
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 > uj для 1 < j < i < n, и ui ∉ Integer ∪ Double ∪ String,
 * то не вычислять xi+1, …, xn и возвратить джамп типа gtJump.
 * <li> Если u1, …, un ∈ Numeric ∪ String, и ui-1 > ui для 1 < i ≤ n, то возвратить значение true.
 */
public class GtFunction extends CompareFunction {
    public static final String name = "gt";

    public GtFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    @Override
    boolean falseCondition(double prev, double next) {
        return prev <= next;
    }
}
