package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция {@code load} имеет один аргумент {@code x}<br/>
 * Пусть {@code x} возвращают значение {@code u}.<br/>
 * Если {@code u} ∉ String, то возвратить джамп типа {@code loadJump}.<br/>
 * Если файл с именем {@code u} не загружается, то возвратить джамп типа {@code loadJump}.<br/>
 * Если содержимое файла с именем {@code u} не является последовательностью ASL-выражений,
 * то возвратить джамп типа {@code loadJump}.<br/>
 * Если {@code e1, …, en} – содержимое файла с именем {@code u}, то вычислить {@code progn(e1, …, en)}.
 */
public class Load extends FileDataFunctionEvaluator {
    public static final String name = "load";

    public Load(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        String fileName = evalArgAsFileName(context);
        List<ASLObject> aslExpressions = readExpressionsFromFile(fileName);
        return new Progn(aslExpressions).evaluate(context);
    }
}
