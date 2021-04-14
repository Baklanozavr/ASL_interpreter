package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.StringAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsString extends TypeCheckFunction {
    public static final String name = "isString";

    public IsString(@NotNull List<ASLObject> arguments) {
        super(StringAtom.class, name, arguments);
    }
}
