package asl.model.core;

import asl.model.core.functions.AddFunction;
import asl.model.core.functions.ArefFunction;
import asl.model.core.functions.ConzFunction;
import asl.model.core.functions.ConzqFunction;
import asl.model.core.functions.QuoteFunction;
import asl.model.core.functions.SetqFunction;

import static asl.model.core.Attributes.*;

public class GlobalContext extends Attributon {
    public GlobalContext() {
        // инициализация предопределённых функций
        Attributon functions = new Attributon();

        functions.put(ADD, AddFunction.INSTANCE);
        functions.put(AREF, ArefFunction.INSTANCE);
        functions.put(CONZ, ConzFunction.INSTANCE);
        functions.put(CONZQ, ConzqFunction.INSTANCE);
        functions.put(QUOTE, QuoteFunction.INSTANCE);
        functions.put(SETQ, SetqFunction.INSTANCE);

        put(FUNCTIONS, functions);
    }

    /**
     * Все функции языка ASL тотальны, т. е. они принимают любые аргументы типа Thing и возвращают значение типа Thing.
     * Поэтому их перегрузка возможна только по числу аргументов.
     */
    public Thing getFunction(QNameAtom functionName, IntegerAtom argNumber) {
        return get(FUNCTIONS).get(functionName).get(argNumber);
    }

    public Thing getFunction(QNameAtom functionName, int argNumber) {
        return getFunction(functionName, new IntegerAtom(argNumber));
    }
}
