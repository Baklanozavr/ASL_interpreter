package asl.input;

import asl.model.system.Context;

import java.util.ArrayList;
import java.util.List;

public class ConsumerBuilder {
    private final List<ASLConsumer> chainOfConsumers = new ArrayList<>();

    public ConsumerBuilder(ApplicationOptions options) {
        if (options.isDebug) {
            chainOfConsumers.add(new ExpressionPrinter(options.inputPrefix, options.output));
        }
    }

    public ConsumerBuilder setContext(Context context) {
        chainOfConsumers.add(new ExpressionEvaluator(context));
        return this;
    }

    public ASLConsumer build() {
        switch (chainOfConsumers.size()) {
            case 0:
                throw new IllegalStateException("Not enough parameters!");
            case 1:
                return chainOfConsumers.get(0);
            default:
                return new SequentialConsumer(chainOfConsumers);
        }
    }
}
