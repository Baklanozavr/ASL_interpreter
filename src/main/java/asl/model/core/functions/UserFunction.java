package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Variable;
import asl.model.system.Context;
import asl.model.system.GlobalContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

public final class UserFunction extends DefinedFunction {
    public static Caller createCaller(boolean isSpecial,
                                      boolean isVaried,
                                      PrognFunction body,
                                      List<Variable> localVariables) {
        return new Caller(isSpecial, isVaried, body, localVariables);
    }

    public UserFunction(@NotNull String name, @NotNull List<ASLObject> arguments) {
        super(name, arguments);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        Caller caller = GlobalContext.INSTANCE.getFunction(name)
                .orElseThrow(() -> new Jump(FUNCTION_CALL_JUMP, "Unknown function! " + name));

        // checking arguments number
        int varsNumber = caller.localVariables.size();
        int argsNumber = arguments.size();
        if (!caller.isVaried && varsNumber != argsNumber)
            throw new Jump(FUNCTION_CALL_JUMP,
                    "Unexpected arguments number: " + argsNumber + ", expected: " + varsNumber);

        if (caller.isVaried && varsNumber > argsNumber)
            throw new Jump(FUNCTION_CALL_JUMP,
                    "Unexpected arguments number: " + argsNumber + ", expected at least: " + (varsNumber - 1));

        // todo: вставить обработку аргументов для случая isVaried

        // local context creation
        Context localContext = new Context(context);
        for (int i = 0; i < caller.localVariables.size(); ++i) {
            Variable localVariable = caller.localVariables.get(i);
            ASLObject argument = arguments.get(i);
            ASLObject varValue = caller.isSpecial ? argument : argument.evaluate(context);
            localContext.putVariable(localVariable.name(), varValue);
        }
        return caller.body.evaluate(localContext);
    }

    public static class Caller {
        private final boolean isSpecial;
        private final boolean isVaried;
        private final PrognFunction body;
        private final List<Variable> localVariables;

        Caller(boolean isSpecial, boolean isVaried, PrognFunction body, List<Variable> localVariables) {
            this.isSpecial = isSpecial;
            this.isVaried = isVaried;
            this.body = body;
            this.localVariables = localVariables;
        }
    }
}
