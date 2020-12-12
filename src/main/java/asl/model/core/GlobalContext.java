package asl.model.core;

public class GlobalContext extends Attributon {
    public GlobalContext() {
        put("functions", new Attributon());
    }

    public Thing getFunction(QNameAtom functionName, IntegerAtom argNumber) {
        return get("functions").get(functionName).get(argNumber);
    }

    public void init() {

    }
}
