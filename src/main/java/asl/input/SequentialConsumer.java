package asl.input;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/** Causes sequential consumption of a same ASL-expression by a list of ASLConsumers */
public class SequentialConsumer implements ASLConsumer {
    private final List<ASLConsumer> consumers;

    public SequentialConsumer(@NotNull List<ASLConsumer> consumers) {
        this.consumers = consumers;
    }

    @Override
    public void consume(ASLObject expr) {
        for (ASLConsumer consumer : consumers) {
            consumer.consume(expr);
        }
    }
}
