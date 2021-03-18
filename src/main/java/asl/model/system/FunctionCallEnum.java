package asl.model.system;

import asl.model.core.ASLObject;
import asl.model.core.functions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public enum FunctionCallEnum {
    ADD("add", AddFunction.class),
    AREF("aref", ArefFunction.class),
    ASET("aset", AsetFunction.class),
    CONZ("conz", ConzFunction.class),
    CONZQ("conzq", ConzqFunction.class),
    DEFUN("defun", DefunFunction.class),
    DIV("div", DivFunction.class),
    QUOTE("quote", QuoteFunction.class),
    SETQ("setq", SetqFunction.class),
    SUB("sub", SubFunction.class),
    UMINUS("uminus", UMinusFunction.class),
    ;

    private final String functionName;
    private final Class<? extends DefinedFunction> functionClass;

    FunctionCallEnum(String functionName, Class<? extends DefinedFunction> functionClass) {
        this.functionName = functionName;
        this.functionClass = functionClass;
    }

    public String functionName() {
        return functionName;
    }

    public DefinedFunction createFunction(List<ASLObject> arguments) {
        try {
            return (DefinedFunction) functionClass.getConstructors()[0].newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("No available constructor for function " + functionName);
        }
    }
}
