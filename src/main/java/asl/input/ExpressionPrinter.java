package asl.input;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

/** Prints ASL-expression with defined prefix into a {@link PrintStream} */
public class ExpressionPrinter implements ASLConsumer {
    private final String prefix;
    private final @NotNull PrintStream outputStream;

    public ExpressionPrinter(@NotNull PrintStream outputStream) {
        this(null, outputStream);
    }

    public ExpressionPrinter(String prefix, @NotNull PrintStream outputStream) {
        this.prefix = prefix;
        this.outputStream = outputStream;
    }

    @Override
    public void consume(ASLObject expr) {
        String result = prefix != null ? prefix + expr : expr.toString();
        outputStream.println(result);
    }
}
