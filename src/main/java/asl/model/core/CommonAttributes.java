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
    QNameAtom DEFUN_JUMP = QNameAtom.create("defunJump");
    QNameAtom DIV_JUMP = QNameAtom.create("divJump");
    QNameAtom GR_JUMP = QNameAtom.create("grJump");
    QNameAtom GRE_JUMP = QNameAtom.create("greJump");
    QNameAtom LOAD_JUMP = QNameAtom.create("loadJump");
    QNameAtom LT_JUMP = QNameAtom.create("ltJump");
    QNameAtom LTE_JUMP = QNameAtom.create("lteJump");
    QNameAtom MOD_JUMP = QNameAtom.create("modJump");
    QNameAtom MUL_JUMP = QNameAtom.create("mulJump");
    QNameAtom SETQ_JUMP = QNameAtom.create("setqJump");
    QNameAtom SUB_JUMP = QNameAtom.create("subJump");
    QNameAtom UMINUS_JUMP = QNameAtom.create("uminusJump");
}
