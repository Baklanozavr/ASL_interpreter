package asl.input;

import asl.model.core.ASLObject;

/**
 * Represents an operation that accepts an ASL expression and returns no result.
 * Is expected to operate via side-effects.
 */
public interface ASLConsumer {
    void consume(ASLObject expr);
}
