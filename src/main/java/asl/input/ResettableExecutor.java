package asl.input;

import java.io.Reader;
import java.io.StringReader;

public class ResettableExecutor implements ASLExecutor {
    private final ASLLexer aslLexer;
    private final ASLParser aslParser;

    public ResettableExecutor(ASLConsumer aslConsumer) {
        aslLexer = new ASLLexer(new StringReader(""));
        aslParser = new ASLParser(aslLexer, aslConsumer);
    }

    @Override
    public void execute(Reader inputReader) {
        try {
            aslLexer.yyclose();
            aslLexer.yyreset(inputReader);
            aslParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
