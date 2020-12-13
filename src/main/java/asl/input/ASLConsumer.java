package asl.input;

import asl.model.core.Thing;

public interface ASLConsumer {
    void consume(Thing expr);
}
