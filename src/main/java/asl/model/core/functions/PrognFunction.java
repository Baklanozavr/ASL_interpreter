package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция progn имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если i > 0, xi-1 возвращает джамп u, xi – не catch, то возвратить джамп u.
 * <li> Если xn не возвращает джамп, то возвратить значение un.
 * <li> Если xn возвращает джамп u, то возвратить джамп u.
 */
public class PrognFunction extends DefinedFunction {
    public PrognFunction(@NotNull List<ASLObject> arguments) {
        super(FunctionCallEnum.PROGN, arguments);
    }

    @Override
    public @NotNull ASLObject evaluate(Context context) {
        if (arguments.isEmpty())
            return Undef.UNDEF;

        int lastArgIndex = arguments.size() - 1;
        for (int i = 0; i < lastArgIndex; ++i) {
            try {
                arguments.get(i).evaluate(context);
            } catch (Jump jump) {
                ASLObject catchCandidate = arguments.get(i + 1);
                if (catchCandidate instanceof CatchFunction) {
                    context.setJump(jump);
                    return catchCandidate.evaluate(context);
                }
                throw jump;
            }
        }
        return arguments.get(lastArgIndex).evaluate(context);
    }
}
