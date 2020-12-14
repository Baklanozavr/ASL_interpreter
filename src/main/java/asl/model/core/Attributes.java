package asl.model.core;

public interface Attributes {
    // general attributes
    QNameAtom NAME = QNameAtom.create("name");
    QNameAtom BODY = QNameAtom.create("body");
    QNameAtom JUMP = QNameAtom.create("jump");
    QNameAtom VALUE = QNameAtom.create("value");
    QNameAtom VARIABLES = QNameAtom.create("variables");
    QNameAtom PARENT = QNameAtom.create("parent");
    QNameAtom FUNCTION = QNameAtom.create("function");
    QNameAtom FUNCTIONS = QNameAtom.create("functions");

    // types
    QNameAtom VARIABLE = QNameAtom.create("Variable");
    QNameAtom ATTRIBUTON_VARIABLE = QNameAtom.create("AttributonVariable");
    QNameAtom FUNCTION_CALL = QNameAtom.create("FunctionCall");

    // jump types
    QNameAtom RETURN_JUMP = QNameAtom.create("returnJump");
    QNameAtom VARIABLE_JUMP = QNameAtom.create("variableJump");
    QNameAtom ATTRIBUTON_VARIABLE_JUMP = QNameAtom.create("attributonVariableJump");
    QNameAtom FUNCTION_CALL_JUMP = QNameAtom.create("functionCallJump");
    QNameAtom ADD_JUMP = QNameAtom.create("addJump");
    QNameAtom AREF_JUMP = QNameAtom.create("arefJump");
    QNameAtom CONZQ_JUMP = QNameAtom.create("conzQJump");
    QNameAtom SETQ_JUMP = QNameAtom.create("setqJump");
    QNameAtom UMINUS_JUMP = QNameAtom.create("uminusJump");

    // functions
    QNameAtom ADD = QNameAtom.create("add");
    QNameAtom AREF = QNameAtom.create("aref");
    QNameAtom CONZ = QNameAtom.create("conz");
    QNameAtom CONZQ = QNameAtom.create("conzq");
    QNameAtom QUOTE = QNameAtom.create("quote");
    QNameAtom SETQ = QNameAtom.create("setq");
    QNameAtom UMINUS = QNameAtom.create("uminus");

}
