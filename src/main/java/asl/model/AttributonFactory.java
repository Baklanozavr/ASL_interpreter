package asl.model;

import asl.model.core.QNameAtom;
import asl.model.core.Sequence;
import asl.model.core.Attributon;
import asl.model.core.Thing;
import asl.model.core.Types;

public class AttributonFactory {
    public static Attributon makeFunctionCall(QNameAtom functionName, Sequence args) {
        return new Attributon()
                .setType(Types.FUNCTION_CALL)
                .put("function", functionName)
                .put("arguments", args);
    }

    public static Attributon makeFunctionCall(String functionName, Thing... args) {
        return makeFunctionCall(QNameAtom.create(functionName), new Sequence(args));
    }

    public static Attributon makeVariable(QNameAtom name) {
        return namedTypedAttributon(name, Types.VARIABLE);
    }

    public static Attributon makeAttributonVariable(QNameAtom name) {
        return namedTypedAttributon(name, Types.ATTRIBUTON_VARIABLE);
    }

    private static Attributon namedTypedAttributon(QNameAtom name, Thing type) {
        return new Attributon()
                .setType(type)
                .put("name", name);
    }
}
