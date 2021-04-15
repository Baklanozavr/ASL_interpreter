package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.StringAtom;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция strConcat имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * Пусть w1, …, wn – результаты конвертации u1, …, un в строки.
 * Если n = 0, то возвратить значение undef.
 * Если n > 0, то
 * Если 0 < i ≤ n, u1, …, ui-1 могут быть конвертированы в строки, элемент ui не может быть конвертирован в строку,
 * то возвратить джамп типа strConcatJump.
 * Если u1, …, un могут быть конвертированы в строки, то породить новую строку w,
 * которая является конкатенацией строк w1, …, wn и возвратить значение w
 */
public class StrConcatFunction extends DefinedFunction {
    public static final String name = "strConcat";

    public StrConcatFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        if (arguments.size() == 0)
            return Undef.UNDEF;

        StringBuilder strBuilder = new StringBuilder();
        for (ASLObject argument : arguments) {
            ASLObject argValue = argument.evaluate(context);
            strBuilder.append(argValue.toString());
        }
        return new StringAtom(strBuilder.toString());
    }
}
