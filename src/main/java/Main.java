import asl.input.ASLConsumerExecutor;
import asl.input.ASLFileProcessor;
import asl.input.ASLParserConsumer;
import asl.model.system.Context;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        if (argv.length == 1) {
            String firstArgument = argv[0];
            Path argPath = Path.of(firstArgument);
            if (Files.isDirectory(argPath)) {
                ASLFileProcessor.readDirectory(argPath);
            } else if (ASLFileProcessor.isAslFile(argPath)) {
                ASLFileProcessor.readFile(argPath);
            } else {
                throw new IllegalArgumentException("Unknown argument: " + firstArgument);
            }
            return;
        }

        var parserConsumer = new ASLParserConsumer(new Context(null), System.out);
        var aslExecutor = new ASLConsumerExecutor(parserConsumer);
        var inputScanner = new Scanner(System.in);
        var codeBuffer = new StringBuilder();
        int curl_counter = 0;
        while (inputScanner.hasNextLine()) {
            String lineOfCode = inputScanner.nextLine();
            codeBuffer.append(lineOfCode);
            if (lineOfCode.contains("{")) ++curl_counter;
            if (lineOfCode.contains("}")) --curl_counter;
            if (curl_counter == 0 && lineOfCode.endsWith(";")) {
                aslExecutor.execute(codeBuffer.toString());
                codeBuffer.setLength(0);
            }
        }
    }
}
