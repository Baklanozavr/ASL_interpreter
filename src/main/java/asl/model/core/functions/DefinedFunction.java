package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.system.Context;
import asl.model.system.FunctionCallEnum;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Пусть состояние sα имеет вид (sβ, oα, oβ),
 * где sβ - множество собственных состояний,
 * oα - непустое множество выходов,
 * oβ - множество константных выходов.
 * <p>
 * Будем говорить, что функция fα определяется как (vα1, vα2, n, Bγ) в состоянии sα,
 * если sβ(οβ.special, fα) = vα1,
 * sβ(οβ.varied, fα) = vα2,
 * и (fα, n, Bγ) ∈ Bβ.
 * <p>
 * Функция fα является специальной в состоянии (sβ, oα), если sβ(oα.special, fα) = special
 * <p>
 * Пусть cδ обозначает вызов предопределенной функции.
 * <p>
 * Компонент special определяет, нужно ли вычислять аргументы вызова функции.
 * Компонент varied определяет, может ли функция иметь переменное число аргументов.
 * В случае varied и местность функции равна m,
 * первые m-1 аргументов вызова функции соответствуют первым m-1 аргументам функции,
 * а аргументы вызова функции, начиная с m-го, объединяются в атрибутон-последовательность,
 * и полученное значение соответствует m-му аргументу функции.
 */
public abstract class DefinedFunction extends ASLObjectWithAttributes {
    private final String name;
    protected final List<ASLObject> arguments;

    protected DefinedFunction(@NotNull FunctionCallEnum functionCallEnum, @NotNull List<ASLObject> arguments) {
        this.name = functionCallEnum.functionName();
        this.arguments = arguments;
    }

    protected void assertArgumentsSize(int expectedSize) {
        if (arguments.size() != expectedSize)
            throw new IllegalArgumentException("Incorrect arguments number! Expected " + expectedSize);
    }

    protected void assertArgumentsSizeMoreThan(int size) {
        if (arguments.size() <= size)
            throw new IllegalArgumentException("Incorrect arguments number! Expected more than " + size);
    }

    /**
     * У предопределённой функции все аргументы всегда вычисляются, если не сказано обратного.
     */
    protected List<ASLObject> evaluateArguments(Context context) {
        return arguments.stream()
                .map(arg -> arg.evaluate(context))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name).append("(");
        for (ASLObject argument : arguments) {
            sb.append(argument.toString()).append(", ");
        }
        if (arguments.size() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.append(")").toString();
    }
}
