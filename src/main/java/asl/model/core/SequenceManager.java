package asl.model.core;

public final class SequenceManager {
    private SequenceManager(){
    }

    public static boolean isSeq(Thing thing) {
        Thing seqLen = thing.get("seqLen");
        return seqLen != null && !Undef.UNDEF.equals(seqLen);
    }
}
