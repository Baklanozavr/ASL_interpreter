package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.NumericAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsNumeric extends TypeCheckFunction {
    public static final String name = "isNumeric";

    public IsNumeric(@NotNull List<ASLObject> arguments) {
        super(NumericAtom.class, name, arguments);
    }
}
