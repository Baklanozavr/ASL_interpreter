package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.FunctionCall;
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
public class Progn extends FunctionEvaluator {
    public static final String name = "progn";

    public Progn(FunctionCall f) {
        super(f);
    }

    public Progn(@NotNull List<ASLObject> arguments) {
        super(new FunctionCall(name, arguments));
    }

    public @NotNull ASLObject evaluate(Context context) {
        if (f.arguments.isEmpty())
            return Undef.UNDEF;

        int lastArgIndex = f.arguments.size() - 1;
        for (int i = 0; i < lastArgIndex; ++i) {
            try {
                f.arguments.get(i).evaluate(context);
            } catch (Jump jump) {
                int nextIndex = i + 1;
                ASLObject catchCandidate = f.arguments.get(nextIndex);
                if (isCatch(catchCandidate)) {
                    context.setJump(jump);
                    ASLObject catchResult = catchCandidate.evaluate(context);
                    if (nextIndex == lastArgIndex)
                        return catchResult;
                } else {
                    throw jump;
                }
            }
        }
        return f.arguments.get(lastArgIndex).evaluate(context);
    }

    private static boolean isCatch(ASLObject catchCandidate) {
        return catchCandidate instanceof FunctionCall &&
                ((FunctionCall) catchCandidate).name.equals(Catch.name);
    }
}
