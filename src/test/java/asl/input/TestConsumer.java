package asl.input;

import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;
import org.junit.Assert;

public class TestConsumer implements ASLConsumer {
    private final Context context = new Context();
    private final GlobalContext globalContext = new GlobalContext();
    private final StringBuilder expressionResultBuilder = new StringBuilder();

    @Override
    public void consume(Thing expr) {
        Context result = expr.eval(context, globalContext);
        if (expressionResultBuilder.length() != 0) {
            expressionResultBuilder.append('\n');
        }
        expressionResultBuilder.append(result.value());
    }

    public void checkResult(String expectedResult) {
        Assert.assertEquals(expectedResult, expressionResultBuilder.toString());
    }
}
