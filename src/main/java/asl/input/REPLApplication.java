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
            if (curl_counter == 0 && lineOfCode.endsWith(";")) {
                try (var stringReader = new StringReader(codeBuffer.toString())) {
                    aslExecutor.execute(stringReader);
                }
                applicationOutput.println(applicationContext.value());
                codeBuffer.setLength(0);
            }
        }
    }
}
