package asl.model.core;

public interface Numeric {
    Number number();

    Numeric plus(Numeric other);

    Numeric minus(Numeric other);
}
