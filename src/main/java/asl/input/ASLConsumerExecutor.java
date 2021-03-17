package asl.input;

import java.io.StringReader;

public class ASLConsumerExecutor implements ASLExecutor {
    private final ASLLexer aslLexer;
    private final ASLParser aslParser;

    public ASLConsumerExecutor(ASLConsumer aslConsumer) {
        aslLexer = new ASLLexer(new StringReader(""));
        aslParser = new ASLParser(aslLexer, aslConsumer);
    }

    @Override
    public void execute(String code) {
        try (var stringReader = new StringReader(code)) {
            aslLexer.yyclose();
            aslLexer.yyreset(stringReader);
            aslParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
