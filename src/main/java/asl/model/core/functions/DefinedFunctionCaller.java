package asl.model.core.functions;

import asl.model.core.ASLObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Map.entry;

public final class DefinedFunctionCaller {
    private static final Map<String, Function<List<ASLObject>, ? extends DefinedFunction>> CALLERS = Map.ofEntries(
            entry(AddFunction.name, AddFunction::new),
            entry(AndFunction.name, AndFunction::new),
            entry(ArefFunction.name, ArefFunction::new),
            entry(AsetFunction.name, AsetFunction::new),
            entry(CatchFunction.name, CatchFunction::new),
            entry(CondFunction.name, CondFunction::new),
            entry(ConzFunction.name, ConzFunction::new),
            entry(ConzqFunction.name, ConzqFunction::new),
            entry(CopySeq.name, CopySeq::new),
            entry(DefunFunction.name, DefunFunction::new),
            entry(DivFunction.name, DivFunction::new),
            entry(EqSeq.name, EqSeq::new),
            entry(GteFunction.name, GteFunction::new),
            entry(GtFunction.name, GtFunction::new),
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
            entry(LoadDataFunction.name, LoadDataFunction::new),
            entry(LoadFunction.name, LoadFunction::new),
            entry(LteFunction.name, LteFunction::new),
            entry(LtFunction.name, LtFunction::new),
            entry(ModFunction.name, ModFunction::new),
            entry(MulFunction.name, MulFunction::new),
            entry(NotFunction.name, NotFunction::new),
            entry(OrFunction.name, OrFunction::new),
            entry(PrognFunction.name, PrognFunction::new),
            entry(QnameFunction.name, QnameFunction::new),
            entry(QuoteFunction.name, QuoteFunction::new),
            entry(SeqAppend.name, SeqAppend::new),
            entry(SeqApply.name, SeqApply::new),
            entry(SeqCons.name, SeqCons::new),
            entry(SeqCopy.name, SeqCopy::new),
            entry(SeqCreate.name, SeqCreate::new),
            entry(SeqFirst.name, SeqFirst::new),
            entry(SeqLength.name, SeqLength::new),
            entry(SeqOne.name, SeqOne::new),
            entry(SeqPrepend.name, SeqPrepend::new),
            entry(SeqRest.name, SeqRest::new),
            entry(SeqReverse.name, SeqReverse::new),
            entry(SetqFunction.name, SetqFunction::new),
            entry(StrConcatFunction.name, StrConcatFunction::new),
            entry(SubFunction.name, SubFunction::new),
            entry(ToBoolean.name, ToBoolean::new),
            entry(ToDouble.name, ToDouble::new),
            entry(ToInteger.name, ToInteger::new),
            entry(ToString.name, ToString::new),
            entry(UMinusFunction.name, UMinusFunction::new),
            entry(WhileFunction.name, WhileFunction::new)
    );

    public static Optional<DefinedFunction> call(@NotNull String functionName,
                                                 @NotNull List<ASLObject> arguments) {
        Function<List<ASLObject>, ? extends DefinedFunction> function = CALLERS.get(functionName);
        return Optional.ofNullable(function)
                .map(caller -> caller.apply(arguments));
    }

    public static boolean contains(String functionName) {
        return CALLERS.containsKey(functionName);
    }

    private DefinedFunctionCaller() {
    }
}
