package asl.model.core.functions;

import asl.model.core.FunctionCall;

/**
 * Функция lt имеет аргументы (x1, …, xn). Пусть < - бинарная операция «меньше» на числах или строках.
 * Функция lt определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если n ≤ 1, то возвратить значение true.
 * <li> Если n > 1, то
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 < uj для 1 < j < i < n, и неверно, что ui-1 < ui,
 * то не вычислять xi+1, …, xn и возвратить значение false.
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 < uj для 1 < j < i < n, и ui ∉ Integer ∪ Double ∪ String,
 * то не вычислять xi+1, …, xn и возвратить джамп типа ltJump.
 * <li> Если u1, …, un ∈ Integer ∪ Double ∪ String, и ui-1 < ui для 1 < i ≤ n, то возвратить значение true.
 */
public class Lt extends CompareFunction {
    public static final String name = "lt";

    public Lt(FunctionCall f) {
        super(f);
    }

    @Override
    boolean falseCondition(double prev, double next) {
        return prev >= next;
    }
}
