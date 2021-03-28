package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Variable;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

public final class UserFunction extends DefinedFunction {
    public static Caller createCaller(boolean isSpecial,
                                      boolean isVaried,
                                      String name,
                                      PrognFunction body,
                                      List<Variable> localVariables) {
        return new Caller(isSpecial, isVaried, name, body, localVariables);
    }

    private final Caller caller;

    private UserFunction(Caller caller, @NotNull List<ASLObject> arguments) {
        super(caller.name, arguments);
        this.caller = caller;
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Context localContext = createLocalContext(context);
        return caller.body.evaluate(localContext);
    }

    private Context createLocalContext(Context context) {
        Context localContext = new Context(context);
        for (int i = 0; i < caller.localVariables.size(); ++i) {
            Variable localVariable = caller.localVariables.get(i);
            ASLObject argument = arguments.get(i);
            ASLObject varValue = caller.isSpecial ? argument : argument.evaluate(context);
            localContext.putVariable(localVariable.name(), varValue);
        }
        return localContext;
    }

    public static class Caller {
        private final boolean isSpecial;
        private final boolean isVaried;
        private final String name;
        private final PrognFunction body;
        private final List<Variable> localVariables;

        Caller(boolean isSpecial, boolean isVaried, String name, PrognFunction body, List<Variable> localVariables) {
            this.isSpecial = isSpecial;
            this.isVaried = isVaried;
            this.name = name;
            this.body = body;
            this.localVariables = localVariables;
        }

        public UserFunction callOn(@NotNull List<ASLObject> arguments) {
            // checking arguments number
            int varsNumber = localVariables.size();
            int argsNumber = arguments.size();
            if (!isVaried && varsNumber != argsNumber)
                throw new Jump(FUNCTION_CALL_JUMP,
                        "Unexpected arguments number: " + argsNumber + ", expected: " + varsNumber);

            if (isVaried && varsNumber > argsNumber)
                throw new Jump(FUNCTION_CALL_JUMP,
                        "Unexpected arguments number: " + argsNumber + ", expected at least: " + (varsNumber - 1));

            // todo: вставить обработку аргументов для случая isVaried

            return new UserFunction(this, arguments);
        }
    }
}
