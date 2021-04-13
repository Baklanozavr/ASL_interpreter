package asl.model.core.functions;

import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.BufferedConsumer;
import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.StringAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

abstract public class FileDataFunction extends DefinedFunction {
    public FileDataFunction(@NotNull String name, @NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSize(1);
    }

    protected String evalArgAsFileName(Context context) {
        return evalArgAs(0, context, StringAtom.class).value();
    }

    protected List<ASLObject> readExpressionsFromFile(String fileName) {
        try (var reader = new BufferedReader(new FileReader(fileName))) {
            List<ASLObject> readExpressions = new ArrayList<>();
            var bufferedConsumer = new BufferedConsumer(readExpressions);
            new ASLParser(new ASLLexer(reader), bufferedConsumer).parse();
            return readExpressions;
        } catch (FileNotFoundException e) {
            throw new Jump(getJumpType(), "the named file does not exist, " +
                    "is a directory rather than a regular file, " +
                    "or for some other reason cannot be opened for reading!");
        } catch (Exception e) {
            throw new Jump(getJumpType());
        }
    }
}
