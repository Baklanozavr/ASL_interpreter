package asl.model.core;

public interface CommonAttributes {
    // jump types
    QNameAtom RETURN_JUMP = QNameAtom.create("returnJump");
    QNameAtom VARIABLE_JUMP = QNameAtom.create("variableJump");
    QNameAtom ATTRIBUTON_VARIABLE_JUMP = QNameAtom.create("attributonVariableJump");
    QNameAtom FUNCTION_CALL_JUMP = QNameAtom.create("functionCallJump");
    QNameAtom ADD_JUMP = QNameAtom.create("addJump");
    QNameAtom AREF_JUMP = QNameAtom.create("arefJump");
    QNameAtom CONZQ_JUMP = QNameAtom.create("conzQJump");
    QNameAtom DIV_JUMP = QNameAtom.create("divJump");
    QNameAtom SETQ_JUMP = QNameAtom.create("setqJump");
    QNameAtom UMINUS_JUMP = QNameAtom.create("uminusJump");
}
