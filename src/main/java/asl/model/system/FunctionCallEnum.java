package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.functions.*;

import java.util.List;
import java.util.function.Function;

public enum FunctionCallEnum {
    ADD("add", AddFunction::new),
    AREF("aref", ArefFunction::new),
    ASET("aset", AsetFunction::new),
    CONZ("conz", ConzFunction::new),
    CONZQ("conzq", ConzqFunction::new),
    DEFUN("defun", DefunFunction::new),
    DIV("div", DivFunction::new),
    GR("gr", GtFunction::new),
    GRE("gre", GteFunction::new),
    JUMP("jump", JumpFunction::new),
    LT("lt", LtFunction::new),
    LTE("lte", LteFunction::new),
    MOD("mod", ModFunction::new),
    MUL("mul", MulFunction::new),
    QUOTE("quote", QuoteFunction::new),
    SETQ("setq", SetqFunction::new),
    SUB("sub", SubFunction::new),
    UMINUS("uminus", UMinusFunction::new),
    ;

    private final String functionName;
    private final Function<List<ASLObject>, ? extends DefinedFunction> functionConstructor;

    FunctionCallEnum(String functionName, Function<List<ASLObject>, ? extends DefinedFunction> functionConstructor) {
        this.functionName = functionName;
        this.functionConstructor = functionConstructor;
    }

    public String functionName() {
        return functionName;
    }

    public DefinedFunction createFunction(List<ASLObject> arguments) {
        return functionConstructor.apply(arguments);
    }
}
