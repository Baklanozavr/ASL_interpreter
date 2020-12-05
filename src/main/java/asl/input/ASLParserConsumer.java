package asl.input;

import asl.model.core.Thing;

import java.util.ArrayList;
import java.util.List;

public class ASLParserConsumer {
    private final List<Thing> expressions = new ArrayList<>();

    public void consume(Thing expr) {
        if (expr == null)
            throw new IllegalArgumentException("Parsed expression can not be null!");
        expressions.add(expr);
    }

    public List<Thing> getExpressions() {
        return expressions;
    }
}
