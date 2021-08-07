package asl.model.core.functions;

import asl.model.core.FunctionCall;

import java.util.stream.IntStream;

public class ForEachSeqDesc extends AbstractForEachSeq {
    public static final String name = "forEachSeqDesc";

    public ForEachSeqDesc(FunctionCall f) {
        super(f);
    }

    @Override
    protected IntStream getIndexStream(int start, int length) {
        int to = start + length;
        return IntStream.range(start, to).map(i -> to - i + start - 1);
    }
}
