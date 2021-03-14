package asl.input;


import asl.model.core.ASLObject;

/**
 * Парсер строит выражения, которые являются
 */
public interface ASLConsumer {
    void consume(ASLObject expr);
}
