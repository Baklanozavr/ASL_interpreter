package asl.model.core;

public interface Attributes {
    // general
    QNameAtom NAME = QNameAtom.create("name");
    QNameAtom BODY = QNameAtom.create("body");
    QNameAtom JUMP = QNameAtom.create("jump");
    QNameAtom VALUE = QNameAtom.create("value");
    QNameAtom VARIABLES = QNameAtom.create("variables");
    QNameAtom FUNCTION = QNameAtom.create("function");

    // types
    QNameAtom VARIABLE = QNameAtom.create("Variable");
    QNameAtom ATTRIBUTON_VARIABLE = QNameAtom.create("AttributonVariable");
    QNameAtom FUNCTION_CALL = QNameAtom.create("FunctionCall");

    // jump types
    QNameAtom RETURN_JUMP = QNameAtom.create("returnJump");
    QNameAtom VARIABLE_JUMP = QNameAtom.create("variableJump");
    QNameAtom ATTRIBUTON_VARIABLE_JUMP = QNameAtom.create("attributonVariableJump");
    QNameAtom FUNCTION_CALL_JUMP = QNameAtom.create("functionCallJump");
    QNameAtom SETQ_JUMP = QNameAtom.create("setqJump");

    // functions
    QNameAtom SETQ = QNameAtom.create("setq");

}
