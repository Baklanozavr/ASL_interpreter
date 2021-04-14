package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.QNameAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IsQName extends TypeCheckFunction {
    public static final String name = "isQName";

    public IsQName(@NotNull List<ASLObject> arguments) {
        super(QNameAtom.class, name, arguments);
    }
}
