package asl.model.core.functions;

import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.BufferedConsumer;
import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.StringAtom;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static asl.model.core.CommonAttributes.LOAD_JUMP;

/**
 * Функция {@code load} имеет один аргумент {@code x}<br/>
 * Пусть {@code x} возвращают значение {@code u}.<br/>
 * Если {@code u} ∉ String, то возвратить джамп типа {@code loadJump}.<br/>
 * Если файл с именем {@code u} не загружается, то возвратить джамп типа {@code loadJump}.<br/>
 * Если содержимое файла с именем {@code u} не является последовательностью ASL-выражений,
 * то возвратить джамп типа {@code loadJump}.<br/>
 * Если {@code e1, …, en} – содержимое файла с именем {@code u}, то вычислить {@code progn(e1, …, en)}.
 */
public class LoadFunction extends DefinedFunction {
    public LoadFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.LOAD, arguments);
        assertArgumentsSize(1);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        ASLObject argValue = arguments.get(0).evaluate(context);
        if (!(argValue instanceof StringAtom))
            throw new Jump(LOAD_JUMP, "argument is not a valid string!");

        String fileName = ((StringAtom) argValue).value();
        List<ASLObject> aslExpressions = readExpressionsFromFile(fileName);
        return new PrognFunction(aslExpressions).evaluate(context);
    }

    private List<ASLObject> readExpressionsFromFile(String fileName) {
        try (var reader = new BufferedReader(new FileReader(fileName))) {
            List<ASLObject> readExpressions = new ArrayList<>();
            var bufferedConsumer = new BufferedConsumer(readExpressions);
            new ASLParser(new ASLLexer(reader), bufferedConsumer).parse();
            return readExpressions;
        } catch (FileNotFoundException e) {
            throw new Jump(LOAD_JUMP, "the named file does not exist, " +
                    "is a directory rather than a regular file, " +
                    "or for some other reason cannot be opened for reading!");
        } catch (Exception e) {
            throw new Jump(LOAD_JUMP);
        }
    }
}
