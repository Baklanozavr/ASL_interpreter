package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsAttributon extends TypeCheckFunction {
    public static final String name = "isAttributon";

    public IsAttributon(@NotNull List<ASLObject> arguments) {
        super(ASLObjectWithAttributes.class, name, arguments);
    }
}
