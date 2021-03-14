package asl.input;

import asl.model.core.ASLObject;
import asl.model.system.Context;

import java.io.PrintStream;

public class ASLParserConsumer implements ASLConsumer {
    private final Context context;
    private final PrintStream outputStream;

    public ASLParserConsumer(Context initialContext, PrintStream outputStream) {
        context = initialContext;
        this.outputStream = outputStream;
    }

    @Override
    public void consume(ASLObject expr) {
        if (expr == null)
            throw new IllegalArgumentException("Parsed expression can not be null!");

        outputStream.println("> " + expr + ";");

        ASLObject result = expr.evaluateToContext(context);

        outputStream.println(result);
        outputStream.println();
    }
}
