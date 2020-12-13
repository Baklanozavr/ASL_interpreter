package asl.input;

import asl.model.core.Context;
import asl.model.core.GlobalContext;
import asl.model.core.Thing;

public class ASLParserConsumer implements ASLConsumer {
    private final Context context;
    private final GlobalContext globalContext;

    public ASLParserConsumer(Context initialContext, GlobalContext initialGlobalContext) {
        context = initialContext;
        globalContext = initialGlobalContext;
    }

    @Override
    public void consume(Thing expr) {
        if (expr == null)
            throw new IllegalArgumentException("Parsed expression can not be null!");

        System.out.println("<<<\n" + expr);
        Context result = expr.eval(context, globalContext);
        System.out.println(">>>");

        Thing jump = result.jump();
        if (jump.defined())
            throw new IllegalStateException("JUMP!\n" + jump);

        System.out.println("result:\n" + result.value());
    }
}
