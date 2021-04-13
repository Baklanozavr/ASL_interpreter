package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class AsetFunction extends DefinedFunction {
    public static final String name = "aset";

    public AsetFunction(@NotNull List<ASLObject> arguments) {
        super(name, Collections.emptyList());
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        // todo: реализовать
        return null;
    }
}
