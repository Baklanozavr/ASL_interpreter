package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.Jump;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.isInteger;

/**
 * Функция toDouble имеет аргументы (x) и определяется следующим образом:
 * <p>
 * Пусть x возвращает значение u, w – результат приведения u к Double.
 * <p>
 * Если u ∈ Double, то w = u.
 * Если u ∈ Integer, то u приводится к Double без потери точности.
 * <p>
 * Если u ∉ Integer ∪ Double, то u не приводится к Double.
 * Если u приводится к Double, то возвратить значение w.
 * <p>
 * Если u не приводится к Double, то возвратить джамп типа toDoubleJump
 */
public class ToDouble extends TypeCastFunction<DoubleAtom> {
    public static final String name = "toDouble";

    public ToDouble(@NotNull List<ASLObject> arguments) {
        super(DoubleAtom.class, name, arguments);
    }

    @Override
    protected DoubleAtom additionalCast(ASLObject argValue) {
        if (isInteger(argValue)) return DoubleAtom.of(getDouble(argValue));
        throw new Jump(getJumpType());
    }
}
