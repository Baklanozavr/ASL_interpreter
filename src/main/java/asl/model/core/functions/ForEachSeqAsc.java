package asl.model.core.functions;

import asl.model.core.FunctionCall;

import java.util.stream.IntStream;

public class ForEachSeqAsc extends AbstractForEachSeq {
    public static final String name = "forEachSeqAsc";

    public ForEachSeqAsc(FunctionCall f) {
        super(f);
    }

    @Override
    protected IntStream getIndexStream(int start, int length) {
        return IntStream.range(start, start + length);
    }
}
