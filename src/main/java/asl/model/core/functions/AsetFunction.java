package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class AsetFunction extends DefinedFunction {
    public AsetFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.ASET, Collections.emptyList());
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        // todo: реализовать
        return null;
    }
}
