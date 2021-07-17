package asl.input;

import asl.model.system.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ASLFileProcessor {
    public static boolean isAslFile(Path filePath) {
        return Files.isRegularFile(filePath) && filePath.getFileName().toString().endsWith(".asl");
    }

    public static void readDirectory(Path directoryPath) {
        try (var filePathStream = Files.list(directoryPath)) {
            filePathStream
                    .filter(ASLFileProcessor::isAslFile)
                    .forEach(ASLFileProcessor::readFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(Path filePath) {
        try (var reader = Files.newBufferedReader(filePath)) {
            var parserConsumer = new ASLParserConsumer(new Context(null), System.out);
            new ASLParser(new ASLLexer(reader), parserConsumer).parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
