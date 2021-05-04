package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.Jump;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция aset имеет аргументы (x0, x1, …, xn, y, z) и определяется следующим образом:
 * Пусть aref(x0, x1, …, xn) возвращает значение u.
 * Если u ∉ Attributon, то возвратить джамп типа asetJump.
 * Если u ∈ Attributon, то
 * Пусть у возвращает значение uy.
 * Если z возвратить джамп типа Undef, то удалить атрибут uy у атрибутона u.
 * Если z возвращает джамп w, который не имеет тип Undef, то возвратить джамп w.
 * Если z не возвращает джамп и возвращает значение uz, то присвоить значение uz атрибуту uy атрибутона u
 * и возвратить значение uz
 */
public final class AsetFunction extends DefinedFunction {
    public static final String name = "aset";

    public AsetFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(2);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        int argsSize = arguments.size(); // at least 3
        var attributon = new ArefFunction(arguments.subList(0, argsSize - 2)).evaluate(context);
        if (!(attributon instanceof ASLObjectWithAttributes))
            throw new Jump(getJumpType());

        ASLObject attribute = arguments.get(argsSize - 2).evaluate(context);
        ASLObject attrValue = arguments.get(argsSize - 1).evaluate(context);
        ((ASLObjectWithAttributes) attributon).put(attribute, attrValue);
        return attrValue;
    }
}
