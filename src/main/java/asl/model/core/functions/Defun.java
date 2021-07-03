package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.core.Undef;
import asl.model.core.Variable;
import asl.model.system.Context;
import asl.model.system.GlobalContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static asl.model.core.BooleanAtom.TRUE;

/**
 * Функция {@code defun} имеет аргументы (special, varied, name, body, arg1, arg2, ...) и определяется следующим образом: <br/>
 * <li> special - принимает два значения: undef или special, если НЕ special, функция сразу вычисляет аргументы
 * <li> varied - признак того, что аргументов может быть сколько угодно
 * <li> name - имя функции
 * <li> body - тело функции, всегда вызов функции progn
 * <li> arg1, arg2, ... - аргументы функции
 */
public final class Defun extends FunctionEvaluator {
    public static final String name = "defunFun";

    private static final String SPECIAL_NO_ARGS_ERR_MSG = "special function should have at least one argument!";
    private static final String VARIED_NO_ARGS_ERR_MSG =
            "function with varied arguments number should have at least one argument!";

    public Defun(FunctionCall f) {
        super(f);
        assertArgumentsSizeMoreThan(3);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        boolean isSpecial = f.arguments.get(0).evaluate(context).equals(TRUE);
        boolean isVaried = f.arguments.get(1).evaluate(context).equals(TRUE);
        String name = evalArgAs(2, context, QNameAtom.class).value();
        if (SystemFunctions.contains(name))
            throw new Jump(getJumpType(), "system function name");

        boolean hasNoArgs = f.arguments.size() == 4;
        if (hasNoArgs) {
            if (isSpecial) throw new Jump(getJumpType(), SPECIAL_NO_ARGS_ERR_MSG);
            if (isVaried) throw new Jump(getJumpType(), VARIED_NO_ARGS_ERR_MSG);
        }

        FunctionCall f = getArgAs(3, FunctionCall.class);
        Progn body = new Progn(Progn.name.equals(f.name) ? f.arguments : List.of(f));

        List<Variable> localVariables = new ArrayList<>(this.f.arguments.size() - 4);
        for (int i = 4; i < this.f.arguments.size(); ++i) {
            localVariables.add(getArgAs(i, Variable.class));
        }
        GlobalContext.INSTANCE.addUserFunction(isSpecial, isVaried, name, body, localVariables);
        return Undef.UNDEF;
    }
}
