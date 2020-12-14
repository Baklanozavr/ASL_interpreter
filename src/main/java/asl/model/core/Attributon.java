package asl.model.core;

import asl.model.SequenceFacade;
import asl.model.core.jumps.FunctionCallJump;
import asl.model.core.jumps.VariableJump;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static asl.model.core.Attributes.*;
import static asl.model.core.Undef.UNDEF;

/**
 * Базовый тип для элементов, которые могут иметь атрибуты<p>
 * Любой элемент данного типа может рассматриваться как последовательность, если у него есть атрибут "seqLen"
 */
public class Attributon extends Thing {
    protected final Map<Thing, Thing> attributes = new HashMap<>();

    @Override
    public @NotNull Thing get(Thing attribute) {
        Thing attrValue = attributes.get(attribute);
        return Objects.requireNonNullElse(attrValue, UNDEF);
    }

    @Override
    public @NotNull Context eval(Context lc, GlobalContext gc) {
        if (is(FUNCTION_CALL)) {
            // получить "сырые" имя функции и количество аргументов
            Thing functionName = get(FUNCTION);
            Optional<IntegerAtom> seqLength = SequenceFacade.getSequenceLength(this);

            // проверить, что имя и количество заданы корректно
            if (!(functionName instanceof QNameAtom) || seqLength.isEmpty())
                return lc.setJump(new FunctionCallJump());

            // получить имя функции и количество аргументов
            QNameAtom functionQName = (QNameAtom) functionName;
            IntegerAtom argsNumber = seqLength.get();

            // получить функцию, проверить корректность
            Thing function = gc.getFunction(functionQName, argsNumber);
            if (function.undefined())
                return lc.setJump(new FunctionCallJump());
//            Thing funcBody = function.get(BODY);
//            if (SequenceFacade.isNotSequence(function) || UNDEF.equals(funcBody))
//                return lc.setJump(new FunctionCallJump());

//            // вычислить аргументы в текущем контекста, положить их в новый контекст
            // создать контекст вычисления и переложить туда аргументы
            Context funcContext = new Context(lc);
            Attributon funcVariables = funcContext.variables();
            for (int i = 1; i <= argsNumber.value(); ++i) {
//                Context evaluatedArgument = get(i).eval(lc, gc);
//                Thing argResultJump = evaluatedArgument.jump();
//                // остановка, если вычисление возвращает jump
//                if (!argResultJump.equals(UNDEF))
//                    return lc.setJump(argResultJump);

//                funcContext.variables().put(variableName, evaluatedArgument.value());
                funcVariables.put(i, get(i));
            }

            // вычислить функцию в новом контексте
            Context evaluatedFunc = function.eval(funcContext, gc);
            evaluatedFunc.returnTo(lc);
            Thing funcJump = evaluatedFunc.jump();
            if (funcJump.is(RETURN_JUMP)) {
                return lc.setValue(funcJump.get(VALUE));
            }
            if (!funcJump.equals(UNDEF)) {
                return lc.setJump(funcJump);
            }
            return lc.setValue(evaluatedFunc.value());
        } else if (is(VARIABLE)) {
            Thing variableName = get(NAME);
            return variableName instanceof QNameAtom
                    ? lc.setValue(lc.variables().get(this))
                    : lc.setJump(new VariableJump());
        }
        return lc.setValue(this);
    }

    public @NotNull Attributon put(@NotNull Thing attrKey, Thing attrValue) {
        if (attrValue == null || UNDEF.equals(attrValue)) {
            attributes.remove(attrKey);
        } else {
            attributes.put(attrKey, attrValue);
        }
        return this;
    }

    public Attributon put(@NotNull String attrName, Thing attrValue) {
        return put(QNameAtom.create(attrName), attrValue);
    }

    public Attributon put(int index, Thing attrValue) {
        return put(new IntegerAtom(index), attrValue);
    }

    public Attributon put(String attrName, String attrValue) {
        return put(QNameAtom.create(attrName), QNameAtom.create(attrValue));
    }

    public Attributon setType(@NotNull Thing type) {
        return put(type, BooleanAtom.TRUE);
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    /**
     * From Map's javadoc: "great care must be exercised if mutable objects are used as map keys..."
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map<Thing, Thing> oAttributes = ((Attributon) o).attributes;
        if (attributes.size() != oAttributes.size()) return false;
        return attributes.entrySet().stream().allMatch(entry -> {
            Thing thisKey = entry.getKey();
            Thing oValue = oAttributes.get(thisKey);
            return oValue != null && oValue.equals(entry.getValue());
        });
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(
                attributes.entrySet().stream()
                        .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                        .toArray(Thing[]::new)
        );
    }

    /**
     * Строковое представление зависит от того, последовательность или нет
     */
    @Override
    public String toString() {
        if (SequenceFacade.isSequence(this))
            return SequenceFacade.sequenceToString(this);

        if (is(VARIABLE))
            return "$" + get(NAME);

        return attributonString();
    }

    public String attributonString() {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        attributes.forEach((key, value) -> {
            if (key != null && value != null)
                stringBuilder
                        .append(key.toString())
                        .append(" = ")
                        .append(value.toString())
                        .append(",\n");
        });
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.append("\n}").toString();
    }

    @Override
    public String toDebugString() {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        attributes.forEach((key, value) -> {
            if (key != null && value != null)
                stringBuilder
                        .append(key.toDebugString())
                        .append(" = ")
                        .append(value.toDebugString())
                        .append(",\n");
        });
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.append("\n}").toString();
    }
}
