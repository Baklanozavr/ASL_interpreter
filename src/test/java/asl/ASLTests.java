package asl;

import asl.input.ASLExecutor;
import asl.input.ASLLexer;
import asl.input.ASLParser;
import asl.input.ApplicationOptions;
import asl.input.ConsumerBuilder;
import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ASLTests {
    private static final ASLExecutor testExecutor = new ASLExecutor() {
        private final Context testContext = Context.empty();
        private final ASLLexer aslLexer = new ASLLexer(new StringReader(""));
        private final ASLParser aslParser = new ASLParser(
                aslLexer,
                new ConsumerBuilder(ApplicationOptions.builder().build()).setContext(testContext).build()
        );

        @Override
        public void execute(Reader inputReader) {
            try {
                aslLexer.yyclose();
                aslLexer.yyreset(inputReader);
                aslParser.parse();
                testContext.clear();
            } catch (Exception e) {
                Assert.fail(e.toString());
            }
        }
    };

    private void executeFile(Path filePath) {
        try (var reader = Files.newBufferedReader(filePath)) {
            testExecutor.execute(reader);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void arithmetic() {
        executeFile(Path.of("./asl/arithmetic.asl"));
    }

    @Test
    public void deepGraphComparison() {
        executeFile(Path.of("./asl/deep_graph_comparison.asl"));
    }

    @Test
    public void logic() {
        executeFile(Path.of("./asl/logic.asl"));
    }

    @Test
    public void numberCompare() {
        executeFile(Path.of("./asl/number_compare.asl"));
    }

    @Test
    public void sequences() {
        executeFile(Path.of("./asl/sequences.asl"));
    }

    @Test
    public void strings() {
        executeFile(Path.of("./asl/strings.asl"));
    }
}
