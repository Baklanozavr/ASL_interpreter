package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Undef;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsUndef extends TypeCheckFunction {
    public static final String name = "isUndef";

    public IsUndef(@NotNull List<ASLObject> arguments) {
        super(Undef.class, name, arguments);
    }
}
