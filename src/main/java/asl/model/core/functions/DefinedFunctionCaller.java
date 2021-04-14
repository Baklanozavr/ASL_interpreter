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
            entry(ArefFunction.name, ArefFunction::new),
            entry(AsetFunction.name, AsetFunction::new),
            entry(CatchFunction.name, CatchFunction::new),
            entry(ConzFunction.name, ConzFunction::new),
            entry(ConzqFunction.name, ConzqFunction::new),
            entry(DefunFunction.name, DefunFunction::new),
            entry(DivFunction.name, DivFunction::new),
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
            entry(PrognFunction.name, PrognFunction::new),
            entry(QuoteFunction.name, QuoteFunction::new),
            entry(SetqFunction.name, SetqFunction::new),
            entry(SubFunction.name, SubFunction::new),
            entry(ToBoolean.name, ToBoolean::new),
            entry(ToDouble.name, ToDouble::new),
            entry(ToInteger.name, ToInteger::new),
            entry(ToString.name, ToString::new),
            entry(UMinusFunction.name, UMinusFunction::new)
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
