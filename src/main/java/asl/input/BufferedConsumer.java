package asl.input;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/** Stores ASL-expression into a user-defined buffer */
public class BufferedConsumer implements ASLConsumer {
    @NotNull
    private final List<ASLObject> buffer;

    public BufferedConsumer(@NotNull List<ASLObject> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void consume(ASLObject expr) {
        buffer.add(expr);
    }
}
