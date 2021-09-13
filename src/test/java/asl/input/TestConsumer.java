package asl.input;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.junit.Assert;

public class TestConsumer implements ASLConsumer {
    private final Context output = new Context(null);
    private final StringBuilder expressionResultBuilder = new StringBuilder();

    @Override
    public void consume(ASLObject expr) {
        expr.evaluateToContext(output);
        ASLObject result = output.value();
        if (expressionResultBuilder.length() != 0) {
            expressionResultBuilder.append('\n');
        }
        expressionResultBuilder.append(result);
    }

    public void checkResult(String expectedResult) {
        Assert.assertEquals(expectedResult, expressionResultBuilder.toString());
    }
}
