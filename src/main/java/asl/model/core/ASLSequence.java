package asl.model.core;

/**
 * Конечные последовательности вещей
 */
public class ASLSequence extends Attributon {

    public ASLSequence() {
        attributes.put(ASLQName.create("first"), null);
        attributes.put(ASLQName.create("rest"), null);
    }


}
