package asl.input;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Копит ASL-выражения в пользовательском буфере.
 */
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
