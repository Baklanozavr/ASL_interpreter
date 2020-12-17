package asl.input;

import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;

import java.io.PrintStream;

public class ASLParserConsumer implements ASLConsumer {
    private final Context context;
    private final GlobalContext globalContext;
    private final PrintStream outputStream;

    public ASLParserConsumer(Context initialContext, GlobalContext initialGlobalContext, PrintStream outputStream) {
        context = initialContext;
        globalContext = initialGlobalContext;
        this.outputStream = outputStream;
    }

    @Override
    public void consume(Thing expr) {
        if (expr == null)
            throw new IllegalArgumentException("Parsed expression can not be null!");

        outputStream.println("> " + expr + ";");

        Context result = expr.eval(context, globalContext);
        Thing jump = result.jump();
        if (jump.defined())
            throw new IllegalStateException("JUMP!\n" + jump);

        outputStream.println(result);
        outputStream.println();
    }
}
