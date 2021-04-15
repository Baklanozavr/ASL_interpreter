package asl.model.core.functions;

import asl.model.core.ASLObject;
import asl.model.core.Jump;
import asl.model.core.QNameAtom;
import asl.model.core.StringAtom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Функция qname имеет аргументы (x) и определяется следующим образом:
 * Пусть x возвращает значение u.
 * Если u ∉ QName ∪ String, то возвратить джамп типа qnameJump.
 * Если u ∈ QName, то возвратить значение u.
 * Если u ∈ String, и для u порождалось квалифицированное имя v, то возвратить значение v.
 * Если u ∈ String, и для u не порождалось квалифицированное имя, то породить новое квалифицированное имя для u
 * и возвратить значение, которое является этим квалифицированным именем.
 */
public class QnameFunction extends TypeCastFunction<QNameAtom> {
    public static final String name = "qname";

    public QnameFunction(@NotNull List<ASLObject> arguments) {
        super(QNameAtom.class, name, arguments);
    }

    @Override
    protected QNameAtom additionalCast(ASLObject argValue) {
        if (argValue instanceof StringAtom) return QNameAtom.create((StringAtom) argValue);
        throw new Jump(getJumpType());
    }
}
