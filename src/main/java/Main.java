import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.ASLParserConsumer;
import asl.model.core.Context;
import asl.model.core.GlobalContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class Main {
    public static void main(String[] argv) {
        if (argv.length == 0) {
            System.out.println("No filepath!");
            return;
        }

        try (Reader reader = new BufferedReader(new FileReader(argv[0]))) {
            ASLParserConsumer parserConsumer = new ASLParserConsumer(new Context(), new GlobalContext());
            new ASLParser(new ASLLexer(reader), parserConsumer).parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
