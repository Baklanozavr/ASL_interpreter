package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.Undef;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция progn имеет аргументы (x1, …, xn) и определяется следующим образом:
 * Пусть x1, …, xn возвращают значения u1, …, un.
 * <li> Если i > 0, xi-1 возвращает джамп u, xi – не catch, то возвратить джамп u.
 * <li> Если i > 0, xi-1 возвращает джамп u, xi – catch, то продолжить вычисление аргументов.
 * <li> Если xn не возвращает джамп, то возвратить значение un.
 * <li> Если xn возвращает джамп u, то возвратить джамп u.
 */
public class PrognFunction extends DefinedFunction {
    public static final String name = "progn";

    public PrognFunction(@NotNull List<ASLObject> arguments) {
        super(name, arguments);
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
                int nextIndex = i + 1;
                ASLObject catchCandidate = arguments.get(nextIndex);
                if (catchCandidate instanceof CatchFunction) {
                    context.setJump(jump);
                    ASLObject catchResult = catchCandidate.evaluate(context);
                    if (nextIndex == lastArgIndex)
                        return catchResult;
                } else {
                    throw jump;
                }
            }
        }
        return arguments.get(lastArgIndex).evaluate(context);
    }
}
