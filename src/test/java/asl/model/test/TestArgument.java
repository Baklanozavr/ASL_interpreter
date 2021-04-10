package asl.model.test;

import asl.model.core.ASLObject;
import asl.model.system.Context;
import org.jetbrains.annotations.NotNull;

/**
 * Класс-обёртка для аргументов тестируемых ASL-функций, сохраняет сколько раз он был вычислен
 *
 * @param <T> - класс ожидаемого значения
 */
public class TestArgument<T extends ASLObject> extends ASLObject {
    private final T value;
    private int evalCounter = 0;

    public TestArgument(@NotNull T value) {
        this.value = value;
    }

    public boolean isEvaluated() {
        return evalCounter > 0;
    }

    @Override
    public @NotNull T evaluate(Context context) {
        ++evalCounter;
        return value;
    }

    @Override
    public String toString() {
        return "TestArgument with value " + value.toString();
    }
}
