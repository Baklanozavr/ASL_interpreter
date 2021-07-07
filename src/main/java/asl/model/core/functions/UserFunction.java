package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.core.Variable;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

public final class UserFunction {
    private final boolean isSpecial;
    private final boolean isVaried;
    private final Progn body;
    private final List<Variable> localVariables;

    public UserFunction(boolean isSpecial, boolean isVaried, Progn body, List<Variable> localVariables) {
        this.isSpecial = isSpecial;
        this.isVaried = isVaried;
        this.body = body;
        this.localVariables = localVariables;
    }

    public Evaluator createEvaluator(FunctionCall f) {
        return new Evaluator(f);
    }

    public class Evaluator extends FunctionEvaluator {
        private Evaluator(FunctionCall f) {
            super(f);
        }

        @Override
        public @NotNull ASLObject evaluate(Context context) {
            // checking arguments number
            int varsNumber = localVariables.size();
            int argsNumber = f.arguments.size();
            if (!isVaried && varsNumber != argsNumber)
                throw new Jump(FUNCTION_CALL_JUMP,
                        "Unexpected arguments number: " + argsNumber + ", expected: " + varsNumber);

            int expectedArgsNumber = varsNumber - 1;
            if (isVaried && argsNumber < expectedArgsNumber) {
                throw new Jump(FUNCTION_CALL_JUMP,
                        "Unexpected arguments number: " + argsNumber + ", expected at least: " + expectedArgsNumber);
            }

            // local context creation
            Context localContext = new Context(context);
            int lastLocalVariableIndex = isVaried ? localVariables.size() - 1 : localVariables.size();
            for (int i = 0; i < lastLocalVariableIndex; ++i) {
                Variable localVariable = localVariables.get(i);
                ASLObject argument = f.arguments.get(i);
                ASLObject varValue = isSpecial ? argument : argument.evaluate(context);
                localContext.putVariable(localVariable.name(), varValue);
            }
            if (isVaried) {
                Variable lastLocalVariable = localVariables.get(lastLocalVariableIndex);
                List<ASLObject> lastArgs = f.arguments.subList(lastLocalVariableIndex, f.arguments.size()).stream()
                        .map(arg -> isSpecial ? arg : arg.evaluate(context))
                        .collect(Collectors.toList());
                localContext.putVariable(lastLocalVariable.name(), SequenceFacade.createSequence(lastArgs));
            }
            return body.evaluate(localContext);
        }
    }
}
