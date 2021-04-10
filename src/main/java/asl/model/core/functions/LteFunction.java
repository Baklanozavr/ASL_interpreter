package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция lte имеет аргументы (x1, …, xn). Пусть ≤  - бинарная операция «меньше или равно» на числах или строках.
 * Функция lte определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если n ≤ 1, то возвратить значение true.
 * <li> Если n > 1, то
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 ≤ uj для 1 < j < i < n, и неверно, что ui-1 ≤ ui,
 * то не вычислять xi+1, …, xn и возвратить значение false.
 * <li> Если u1, …, ui ∈ Integer ∪ Double ∪ String, uj-1 ≤ uj для 1 < j < i < n, и ui ∉ Integer ∪ Double ∪ String,
 * то не вычислять xi+1, …, xn и возвратить джамп типа gteJump.
 * <li> Если u1, …, un ∈ Integer ∪ Double ∪ String, и ui-1 ≤ ui для 1 < i ≤ n, то возвратить значение true.
 */
public class LteFunction extends CompareFunction {
    public LteFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.LTE, arguments);
    }

    @Override
    boolean falseCondition(double prev, double next) {
        return prev > next;
    }
}
