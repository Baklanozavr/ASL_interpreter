package asl.model.core;

import asl.model.core.functions.ConzFunction;
import asl.model.core.functions.QuoteFunction;
import asl.model.core.functions.SetqFunction;

import static asl.model.core.Attributes.CONZ;
import static asl.model.core.Attributes.FUNCTIONS;
import static asl.model.core.Attributes.QUOTE;
import static asl.model.core.Attributes.SETQ;

public class GlobalContext extends Attributon {
    public GlobalContext() {
        // инициализация предопределённых функций
        Attributon functions = new Attributon();
        functions.put(SETQ, SetqFunction.INSTANCE);
        functions.put(CONZ, ConzFunction.INSTANCE);
        functions.put(QUOTE, QuoteFunction.INSTANCE);

        put(FUNCTIONS, functions);
    }

    /**
     * Все функции языка ASL тотальны, т. е. они принимают любые аргументы типа Thing и возвращают значение типа Thing.
     * Поэтому их перегрузка возможна только по числу аргументов.
     */
    public Thing getFunction(QNameAtom functionName, IntegerAtom argNumber) {
        return get(FUNCTIONS).get(functionName).get(argNumber);
    }
}
