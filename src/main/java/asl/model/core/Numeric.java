package asl.model.core;

import org.jetbrains.annotations.NotNull;

public interface Numeric {
    @NotNull
    Number number();

    @NotNull
    Numeric plus(Numeric other);

    @NotNull
    Numeric minus(Numeric other);
}
