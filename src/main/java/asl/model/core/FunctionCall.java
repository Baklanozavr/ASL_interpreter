package asl.model.core;

import asl.model.core.functions.SystemFunctions;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

public final class FunctionCall extends ASLObjectWithAttributes {
    private static int idGenerator = 0;

    public final int id = ++idGenerator;
    public final String name;
    public final List<ASLObject> arguments;

    public FunctionCall(@NotNull String name, @NotNull List<ASLObject> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    private FunctionCall(String name, List<ASLObject> arguments, Map<Attribute, ASLObject> attributes) {
        super(attributes);
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        return SystemFunctions.getFor(this)
                .or(() -> Context.getUserFunction(this))
                .orElseThrow(() -> new Jump(FUNCTION_CALL_JUMP, "Unknown function! " + name))
                .evaluate(context);
    }

    @Override
    public @NotNull FunctionCall copyShallow() {
        return new FunctionCall(name, new ArrayList<>(arguments), attrsCopyShallow());
    }

    @Override
    public FunctionCall copyDeepWithoutAttributes() {
        List<ASLObject> copiedArgs = arguments.stream().map(ASLObject::copyDeep).collect(Collectors.toList());
        return new FunctionCall(name, copiedArgs, emptyAttrsMap());
    }

    @Override
    public boolean equalsShallow(ASLObject o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionCall fCall = (FunctionCall) o;
        return name.equals(fCall.name) &&
                attrsEqualsShallow(fCall) &&
                arguments.equals(fCall.arguments);
    }

    @Override
    public boolean equalsDeep(ASLObject o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionCall fCall = (FunctionCall) o;
        return name.equals(fCall.name) &&
                attrsEqualsDeep(fCall) &&
                isArgListEqualsDeep(fCall.arguments);
    }

    private boolean isArgListEqualsDeep(List<ASLObject> args) {
        if (arguments.size() != args.size()) return false;
        for (int i = 0; i < arguments.size(); ++i) {
            var thisArg = arguments.get(i);
            var thatArg = args.get(i);
            if (!thisArg.equalsDeep(thatArg)) return false;
        }
        return true;
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
