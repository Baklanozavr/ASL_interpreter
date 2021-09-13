package asl.model.system;

public class StopSignal extends RuntimeException {
    public StopSignal() {
        super(null, null, false, false);
    }
}
