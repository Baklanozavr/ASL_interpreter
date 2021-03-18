package asl.model.util;

import asl.model.core.ASLObject;
import asl.model.core.DoubleAtom;
import asl.model.core.IntegerAtom;
import asl.model.core.NumericAtom;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class MathUtils {
    private MathUtils() {
    }

    public static boolean isInteger(ASLObject aslObject) {
        return aslObject instanceof IntegerAtom;
    }

    public static boolean isDouble(ASLObject aslObject) {
        return aslObject instanceof DoubleAtom;
    }

    public static boolean isNumeric(ASLObject aslObject) {
        return aslObject instanceof NumericAtom;
    }

    public static boolean isNotNumeric(ASLObject aslObject) {
        return !isNumeric(aslObject);
    }

    public static int getInt(ASLObject aslObject) {
        return ((NumericAtom<?>) aslObject).value().intValue();
    }

    public static double getDouble(ASLObject aslObject) {
        return ((NumericAtom<?>) aslObject).value().doubleValue();
    }

    public static Collector<ASLObject, NumericContainer, NumericAtom<?>> mathCollector(
            Supplier<NumericAtom<?>> numericSupplier,
            BiFunction<ASLObject, ASLObject, NumericAtom<?>> collectFunction
    ) {
        return Collector.of(
                () -> NumericContainer.create(numericSupplier.get()),
                (container, numeric) -> container.setContent(collectFunction.apply(container.content, numeric)),
                (left, right) -> left.setContent(collectFunction.apply(left.content, right.content)),
                NumericContainer::content
        );
    }

    private static class NumericContainer {
        static NumericContainer create(NumericAtom<?> content) {
            return new NumericContainer(content);
        }

        private NumericAtom<?> content;

        private NumericContainer(NumericAtom<?> content) {
            this.content = content;
        }

        public NumericContainer setContent(NumericAtom<?> content) {
            this.content = content;
            return this;
        }

        public NumericAtom<?> content() {
            return content;
        }
    }
}
