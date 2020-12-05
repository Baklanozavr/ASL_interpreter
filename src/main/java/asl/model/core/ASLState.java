package asl.model.core;

/**
 * Состояния определяют входные и выходные данные для выражений, могут иметь дополнительные атрибуты
 */
public class ASLState extends Attributon {
    public ASLState() {
        // variableValue возвращает атрибутон, атрибутами которого являются имена переменных.
        // Переменная $u определена в состоянии s ∈ State, если av(s, variableValue, u) ∉ Undef.
        // В этом случае, говорят, что переменная $u имеет значение av(s, variableValue, u) в состоянии s;
//        attributes.put(ASLQName.VARIABLE_VALUE, null);

        // value хранит последнее вычисленное в выражении значение;
//        attributes.put(ASLQName.VALUE, null);

        // jump возвращает джамп. Если этот атрибут определен, то говорят, что выражение бросило джамп;
//        attributes.put(ASLQName.JUMP, null);

        // previousJump определяет предыдущий джамп (используется при бросании того же самого джампа еще раз);
//        attributes.put(ASLQName.PREVIOUS_JUMP, null);

        // global возвращает глобальный атрибутон, в котором хранятся глобальные значения,
        // с которыми оперирует вычисляемое выражение.
        // Глобальный атрибутон имеет атрибут functions, хранящий информацию о всех определяемых функциях:
        // av(av(av(s, global), f), i) возвращает спецификацию функции с именем f ∈ QName местности i ∈ Integer,
        // где i – целое неотрицательное число.
//        attributes.put(ASLQName.GLOBAL, null);

        // context возвращает внешний контекст вычисления выражения. Пока что не используется.
//        attributes.put(ASLQName.CONTEXT, null);
    }

}
