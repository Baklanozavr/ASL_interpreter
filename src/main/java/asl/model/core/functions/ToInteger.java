package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.util.MathUtils.getDouble;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isDouble;

/**
 * Функция toInteger имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Пусть w – результат приведения u к Integer.
 * Если u ∈ Integer, то w = u.
 * Если u ∈ Double, то
 * Если u приводится к Integer без округления, то w – результат этого приведения.
 * Если u не приводится к Integer без округления, то u не приводится к Integer.
 * Если u ∉ Integer ∪ Double, то u не приводится к Integer.
 * Если u приводится к Integer, то возвратить значение w.
 * Если u не приводится к Integer, то возвратить джамп типа toIntegerJump.
 */
public class ToInteger extends TypeCastFunction<IntegerAtom> {
    public static final String name = "toInteger";

    public ToInteger(@NotNull List<ASLObject> arguments) {
        super(IntegerAtom.class, name, arguments);
    }

    @Override
    protected IntegerAtom additionalCast(ASLObject argValue) {
        if (isDouble(argValue)) {
            int intValue = getInt(argValue);
            if (intValue == getDouble(argValue))
                return IntegerAtom.of(intValue);
        }
        throw new Jump(getJumpType());
    }
}
