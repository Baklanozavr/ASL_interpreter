import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.ASLParserConsumer;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.*;

public class Main {
  public static void main(String[] argv) {
    try (Reader reader = new InputStreamReader(System.in)) {
//      Reader reader = new FileReader(argv[0]);
      ComplexSymbolFactory sf = new ComplexSymbolFactory();
      ASLParser p = new ASLParser(new ASLLexer(reader, sf), sf, new ASLParserConsumer());
      Object result = p.parse().value;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
