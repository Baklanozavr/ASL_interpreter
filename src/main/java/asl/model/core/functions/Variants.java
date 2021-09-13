package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Undef;
import asl.model.system.BranchController;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Порождает новые контексты вычисления, возвращает значение для текущего контекста.
 */
public class Variants extends FunctionEvaluator {
    public static final String name = "variants";

    public Variants(FunctionCall f) {
        super(f);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        final int argSize = f.arguments.size();
        if (argSize == 0) return Undef.UNDEF;

        int variantIndex = BranchController.getVariantIndex(f.id);
        // новая точка ветвления
        if (variantIndex < 0) {
            BranchController.addBranchPoint(f.id, argSize);
            variantIndex = 0;
        }
        return f.arguments.get(variantIndex).evaluate(context);
    }
}
