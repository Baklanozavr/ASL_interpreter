package asl.model.core;

import asl.model.core.functions.SystemFunctions;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

public final class FunctionCall extends ASLObjectWithAttributes {
    public final String name;
    public final List<ASLObject> arguments;

    public FunctionCall(@NotNull String name, @NotNull List<ASLObject> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    private FunctionCall(FunctionCall obj) {
        super(obj.attributes);
        name = obj.name;
        arguments = obj.arguments;
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return SystemFunctions.getFor(this)
            .or(() -> Context.getUserFunction(this))
                .orElseThrow(() -> new Jump(FUNCTION_CALL_JUMP, "Unknown function! " + name))
                .evaluate(context);
    }

    @Override
    public @NotNull FunctionCall clone() {
        return new FunctionCall(this);
    }

    @Override
    public @NotNull String toString() {
        StringBuilder sb = new StringBuilder(name).append("(");
        if (arguments.size() > 0) {
            for (ASLObject argument : arguments) {
                sb.append(argument.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        return sb.append(")").toString();
    }
}
