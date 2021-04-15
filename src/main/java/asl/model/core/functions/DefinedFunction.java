package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.ASLObjectWithAttributes;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static asl.model.core.CommonAttributes.FUNCTION_CALL_JUMP;

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

    public DefinedFunction(@NotNull String name, @NotNull List<ASLObject> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    protected QNameAtom getJumpType() {
        return QNameAtom.create(name + "Jump");
    }

    protected void assertArgumentsSize(int expectedSize) {
        if (arguments.size() != expectedSize)
            throw new Jump(FUNCTION_CALL_JUMP, "Incorrect arguments number! Expected " + expectedSize);
    }

    protected void assertArgumentsSizeMoreThan(int size) {
        if (arguments.size() <= size)
            throw new Jump(FUNCTION_CALL_JUMP, "Incorrect arguments number! Expected more than " + size);
    }

    protected void assertArgumentsSizeLessThan(int size) {
        if (arguments.size() >= size)
            throw new Jump(FUNCTION_CALL_JUMP, "Incorrect arguments number! Expected less than " + size);
    }

    protected <T extends ASLObject> T getArgAs(int argIndex, Class<T> type) {
        ASLObject argument = arguments.get(argIndex);
        return castToType(argument, type);
    }

    protected <T extends ASLObject> T evalArgAs(int argIndex, Context context, Class<T> type) {
        ASLObject argument = arguments.get(argIndex).evaluate(context);
        return castToType(argument, type);
    }

    protected <T extends ASLObject> T castToType(ASLObject obj, Class<T> type) {
        if (type.isInstance(obj))
            return type.cast(obj);
        throw new Jump(getJumpType(), "invalid argument type: " + obj.toString());
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
        if (arguments.size() > 0) {
            for (ASLObject argument : arguments) {
                sb.append(argument.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        return sb.append(")").toString();
    }
}
