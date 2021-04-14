package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.BooleanAtom;
import asl.model.core.Jump;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.BooleanAtom.FALSE;
import static asl.model.core.BooleanAtom.TRUE;
import static asl.model.util.MathUtils.getInt;
import static asl.model.util.MathUtils.isNumeric;

/**
 * Функция toBoolean имеет аргументы (x) и определяется следующим образом:
 * <p>
 * Пусть x возвращает значение u, w – результат приведения u к Boolean.<br/>
 * <ul>
 * <li> Если u ∈ Boolean, то w = u.
 * <li> Если u ∈ Integer ∪ Double, то
 * <ul>
 * <li> Если u = 0, то w = false.
 * <li> Если u ≠ 0, то w = type.
 * </ul>
 * <li> Если u ∉ Boolean ∪ Integer ∪ Double, то u не приводится к Boolean.
 * <li> Если u приводится к Boolean, то возвратить значение w.
 * <li> Если u не приводится к Boolean, то возвратить джамп типа toBooleanJump.
 * </ul>
 */
public class ToBoolean extends TypeCastFunction<BooleanAtom> {
    public static final String name = "toBoolean";

    public ToBoolean(@NotNull List<ASLObject> arguments) {
        super(BooleanAtom.class, name, arguments);
    }

    @Override
    protected BooleanAtom additionalCast(ASLObject argValue) {
        if (isNumeric(argValue)) return getInt(argValue) == 0 ? FALSE : TRUE;
        throw new Jump(getJumpType());
    }
}
