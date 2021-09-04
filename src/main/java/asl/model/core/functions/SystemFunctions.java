package asl.model.core.functions;

import asl.model.core.FunctionCall;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Map.entry;

public final class SystemFunctions {
    private static final Map<String, Function<FunctionCall, ? extends FunctionEvaluator>> SYSTEM_FUNCTIONS = Map.<String, Function<FunctionCall, ? extends FunctionEvaluator>>ofEntries(
            entry(Add.name, Add::new),
            entry(And.name, And::new),
            entry(RefFunction.aref, RefFunction::new),
            entry(RefFunction.globaref, RefFunction::new),
            entry(RefFunction.interef, RefFunction::new),
            entry(SetFunction.aset, SetFunction::new),
            entry(SetFunction.globaset, SetFunction::new),
            entry(SetFunction.inteset, SetFunction::new),
            entry(Assert.name, Assert::new),
            entry(AssertEq.name, AssertEq::new),
            entry(Catch.name, Catch::new),
            entry(Coalesce.name, Coalesce::new),
            entry(Cond.name, Cond::new),
            entry(Conz.name, Conz::new),
            entry(Conzq.name, Conzq::new),
            entry(CopyShallow.name, CopyShallow::new),
            entry(CopyShallow.copySeq, CopyShallow::new),
            entry(CopyShallow.seqCopy, CopyShallow::new),
            entry(Defun.name, Defun::new),
            entry(Div.name, Div::new),
            entry(Eval.name, Eval::new),
            entry(Eq.name, Eq::new),
            entry(EqDeep.name, EqDeep::new),
            entry(EqSeq.name, EqSeq::new),
            entry(EqShallow.name, EqShallow::new),
            entry(ForEach.name, ForEach::new),
            entry(ForEachSeqAsc.name, ForEachSeqAsc::new),
            entry(ForEachSeqDesc.name, ForEachSeqDesc::new),
            entry(Gte.name, Gte::new),
            entry(Gt.name, Gt::new),
            entry(IsAtom.name, IsAtom::new),
            entry(IsAttributon.name, IsAttributon::new),
            entry(IsBoolean.name, IsBoolean::new),
            entry(IsDef.name, IsDef::new),
            entry(IsDouble.name, IsDouble::new),
            entry(IsInteger.name, IsInteger::new),
            entry(IsNumeric.name, IsNumeric::new),
            entry(IsQName.name, IsQName::new),
            entry(IsString.name, IsString::new),
            entry(IsUndef.name, IsUndef::new),
            entry(JumpFunction.name, JumpFunction::new),
            entry(LoadData.name, LoadData::new),
            entry(Load.name, Load::new),
            entry(Lte.name, Lte::new),
            entry(Lt.name, Lt::new),
            entry(Mod.name, Mod::new),
            entry(Mul.name, Mul::new),
            entry(Neq.name, Neq::new),
            entry(Not.name, Not::new),
            entry(Or.name, Or::new),
            entry(Progn.name, Progn::new),
            entry(QnameFunction.name, QnameFunction::new),
            entry(Quote.name, Quote::new),
            entry(SeqAppend.name, SeqAppend::new),
            entry(SeqApply.name, SeqApply::new),
            entry(SeqCons.name, SeqCons::new),
            entry(SeqCreate.name, SeqCreate::new),
            entry(SeqFindIndexByValue.name, SeqFindIndexByValue::new),
            entry(SeqFirst.name, SeqFirst::new),
            entry(SeqLength.name, SeqLength::new),
            entry(SeqOne.name, SeqOne::new),
            entry(SeqPrepend.name, SeqPrepend::new),
            entry(SeqRest.name, SeqRest::new),
            entry(SeqReverse.name, SeqReverse::new),
            entry(Setq.name, Setq::new),
            entry(StrConcat.name, StrConcat::new),
            entry(Sub.name, Sub::new),
            entry(ToBoolean.name, ToBoolean::new),
            entry(ToDouble.name, ToDouble::new),
            entry(ToInteger.name, ToInteger::new),
            entry(ToString.name, ToString::new),
            entry(UMinus.name, UMinus::new),
            entry(WhileFunction.name, WhileFunction::new)
    );

    public static Optional<FunctionEvaluator> getFor(FunctionCall f) {
        return Optional.ofNullable(SYSTEM_FUNCTIONS.get(f.name)).map(fun -> fun.apply(f));
    }

    public static boolean contains(String functionName) {
        return SYSTEM_FUNCTIONS.containsKey(functionName);
    }

    private SystemFunctions() {
    }
}
