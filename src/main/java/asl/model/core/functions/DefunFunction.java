package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.core.Undef;
import asl.model.core.Variable;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import asl.model.system.GlobalContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static asl.model.core.BooleanAtom.TRUE;
import static asl.model.core.CommonAttributes.DEFUN_JUMP;

/**
 * Функция {@code defun} имеет аргументы (special, varied, name, body, arg1, arg2, ...) и определяется следующим образом: <br/>
 * <li> special - принимает два значения: undef или special, если НЕ special, функция сразу вычисляет аргументы
 * <li> varied - признак того, что аргументов может быть сколько угодно
 * <li> name - имя функции
 * <li> body - тело функции, всегда вызов функции progn
 * <li> arg1, arg2, ... - аргументы функции
 */
public final class DefunFunction extends DefinedFunction {
    private static final Set<String> SYSTEM_FUNCTIONS =
            Arrays.stream(FunctionCallEnum.values()).map(FunctionCallEnum::functionName).collect(Collectors.toSet());

    public DefunFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.DEFUN, arguments);
        assertArgumentsSizeMoreThan(4);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        boolean isSpecial = arguments.get(0).evaluate(context).equals(TRUE);
        boolean isVaried = arguments.get(1).evaluate(context).equals(TRUE);
        String name = castToType(arguments.get(2).evaluate(context), QNameAtom.class).value();
        if (SYSTEM_FUNCTIONS.contains(name))
            throw new Jump(DEFUN_JUMP, "system function name");

        PrognFunction body = castToType(arguments.get(3), PrognFunction.class);
        List<Variable> localVariables = new ArrayList<>(arguments.size() - 4);
        for (int i = 4; i < arguments.size(); ++i) {
            localVariables.add(castToType(arguments.get(i), Variable.class));
        }
        GlobalContext.INSTANCE.addFunction(isSpecial, isVaried, name, body, localVariables);
        return Undef.UNDEF;
    }

    private <T extends ASLObject> T castToType(ASLObject argument, Class<T> type) {
        if (type.isInstance(argument))
            return type.cast(argument);

        throw new Jump(DEFUN_JUMP, "invalid argument type: " + argument.toString());
    }
}
