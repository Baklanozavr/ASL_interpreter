package asl.model.core.functions;

import asl.model.core.ASLObject;
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
public final class DefunFunction extends DefinedFunction {
    public static final String name = "defun";

    public DefunFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
        assertArgumentsSizeMoreThan(4);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        boolean isSpecial = arguments.get(0).evaluate(context).equals(TRUE);
        boolean isVaried = arguments.get(1).evaluate(context).equals(TRUE);
        String name = evalArgAs(2, context, QNameAtom.class).value();
        if (DefinedFunctionCaller.contains(name))
            throw new Jump(getJumpType(), "system function name");

        PrognFunction body = getArgAs(3, PrognFunction.class);
        List<Variable> localVariables = new ArrayList<>(arguments.size() - 4);
        for (int i = 4; i < arguments.size(); ++i) {
            localVariables.add(getArgAs(i, Variable.class));
        }
        GlobalContext.INSTANCE.addFunction(isSpecial, isVaried, name, body, localVariables);
        return Undef.UNDEF;
    }
}
