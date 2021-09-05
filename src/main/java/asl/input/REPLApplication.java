package asl.input;

import asl.model.system.Context;

import java.io.PrintStream;
import java.io.StringReader;
import java.util.Scanner;

public class REPLApplication implements ASLApplication {
    private final Context applicationContext;
    private final ASLExecutor aslExecutor;
    private final PrintStream applicationOutput;

    public REPLApplication(Context applicationContext, ApplicationOptions options, ASLExecutor aslExecutor) {
        this.applicationContext = applicationContext;
        this.aslExecutor = aslExecutor;
        this.applicationOutput = options.output;
    }

    @Override
    public void run() {
        var codeBuffer = new StringBuilder();
        int curl_counter = 0;
        var inputScanner = new Scanner(System.in);
        while (inputScanner.hasNextLine()) {
            String lineOfCode = inputScanner.nextLine();
            codeBuffer.append(lineOfCode);
            if (lineOfCode.contains("{")) ++curl_counter;
            if (lineOfCode.contains("}")) --curl_counter;
            if (curl_counter == 0 && endsWithSemicolon(lineOfCode)) {
                try (var stringReader = new StringReader(codeBuffer.toString())) {
                    aslExecutor.execute(stringReader);
                } catch (Exception ignore) {
                }
                applicationOutput.println(applicationContext.value());
                codeBuffer.setLength(0);
            }
        }
    }

    // проверяет, что ; располагается вне комментария
    // todo: учитывать экранированные символы
    private boolean endsWithSemicolon(String inputLine) {
        int commentStartIndex = inputLine.indexOf("//");
        if (commentStartIndex != -1) {
            int quotesCounter = 0;
            for (int i = 0; i < inputLine.length(); ++i) {
                if (inputLine.charAt(i) == '"') {
                    ++quotesCounter;
                    if (i > commentStartIndex) {
                        return endsWithSemicolon(inputLine.substring(i + 1));
                    }
                }
                if (i == commentStartIndex && quotesCounter % 2 == 0) { // комментарий
                    return inputLine.substring(0, commentStartIndex).strip().endsWith(";");
                }
            }
        }
        return inputLine.endsWith(";");
    }
}
