package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsDouble extends TypeCheckFunction {
    public static final String name = "isDouble";

    public IsDouble(@NotNull List<ASLObject> arguments) {
        super(DoubleAtom.class, name, arguments);
    }
}
