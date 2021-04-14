package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.IntegerAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsInteger extends TypeCheckFunction {
    public static final String name = "isInteger";

    public IsInteger(@NotNull List<ASLObject> arguments) {
        super(IntegerAtom.class, name, arguments);
    }
}
