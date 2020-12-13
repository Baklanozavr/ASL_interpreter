package asl.model;

import asl.model.core.Attributon;
import asl.model.core.QNameAtom;
import asl.model.core.Thing;

import static asl.model.core.Attributes.ATTRIBUTON_VARIABLE;
import static asl.model.core.Attributes.FUNCTION;
import static asl.model.core.Attributes.FUNCTION_CALL;
import static asl.model.core.Attributes.NAME;
import static asl.model.core.Attributes.VARIABLE;

public class AttributonFactory {
    public static Attributon makeFunctionCall(QNameAtom functionName, Attributon argsSeq) {
        return (argsSeq == null ? SequenceFacade.createSequence() : argsSeq)
                .setType(FUNCTION_CALL)
                .put(FUNCTION, functionName);
    }

    public static Attributon makeFunctionCall(String functionName, Thing... args) {
        return makeFunctionCall(QNameAtom.create(functionName), SequenceFacade.createSequence(args));
    }

    public static Attributon makeVariable(QNameAtom name) {
        return namedTypedAttributon(name, VARIABLE);
    }

    public static Attributon makeAttributonVariable(QNameAtom name) {
        return namedTypedAttributon(name, ATTRIBUTON_VARIABLE);
    }

    public static Attributon makeSequence(Thing... args) {
        return SequenceFacade.createSequence(args);
    }

    private static Attributon namedTypedAttributon(QNameAtom name, Thing type) {
        return new Attributon()
                .setType(type)
                .put(NAME, name);
    }
}
