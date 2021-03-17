import asl.input.ASLConsumerExecutor;
import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.ASLParserConsumer;
import asl.model.system.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        if (argv.length == 1) {
            try (var reader = new BufferedReader(new FileReader(argv[0]))) {
                var parserConsumer = new ASLParserConsumer(new Context(null), System.out);
                new ASLParser(new ASLLexer(reader), parserConsumer).parse();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        var parserConsumer = new ASLParserConsumer(new Context(null), System.out);
        var aslExecutor = new ASLConsumerExecutor(parserConsumer);
        var inputScanner = new Scanner(System.in);
        var codeBuffer = new StringBuilder();
        while (inputScanner.hasNextLine()) {
            codeBuffer.setLength(0);
            String lineOfCode = inputScanner.nextLine();
            codeBuffer.append(lineOfCode);
            if (lineOfCode.endsWith(";")) {
                aslExecutor.execute(codeBuffer.toString());
            }
        }
    }
}
