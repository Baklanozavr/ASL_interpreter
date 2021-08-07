package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.FunctionCall;
import asl.model.core.IntegerAtom;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.system.SequenceFacade;
import asl.model.util.Sequence;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public abstract class AbstractForEachSeq extends AbstractForEach {
    public AbstractForEachSeq(FunctionCall f) {
        super(f);
    }

    @Override
    public final @NotNull ASLObject evaluate(Context context) {
        ASLObjectWithAttributes attrObj = evalArgAs(2, context, ASLObjectWithAttributes.class);
        Sequence sequence = SequenceFacade.toSequence(attrObj);
        if (sequence == null) throw new Jump(getJumpType(), "Third argument is not a sequence!");
        BiConsumer<ASLObject, ASLObject> indexValueConsumer = getKeyValueConsumer(context);
        IntegerAtom start = sequence.getStart();
        IntegerAtom length = sequence.getSeqLen();
        int intStart = start.value();
        int intLength = length.value();
        getIndexStream(intStart, intLength)
                .mapToObj(IntegerAtom::of)
                .forEach(i -> indexValueConsumer.accept(i, attrObj.get(i)));
        return Undef.UNDEF;
    }

    protected abstract IntStream getIndexStream(int start, int length);
}
