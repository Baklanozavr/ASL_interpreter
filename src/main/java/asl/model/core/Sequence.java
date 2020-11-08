package asl.model.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Последовательность.
 * Обязательно конечна -> образование циклов в графе запрещено для последовательностей
 */
public interface Sequence {
    @NotNull ASLInteger length();

    @Nullable Thing first();

    @Nullable Sequence rest();
}
