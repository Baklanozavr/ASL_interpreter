package asl.input;

import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Ignore
public class ASLInputFromFileTest {
    private static final String INPUT_FILE = "data/TestInput.asl";
    private static final String OUTPUT_FILE = "data/ExpectedOutput";
    private static final String CHARSET_NAME = UTF_8.name();
    private static final String SYSTEM_DELIMITER = "\r\n";

    @Test
    public void readFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String outputData = null;
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(INPUT_FILE);
        Assert.assertNotNull("Input file not found!", inputStream);
        try (
                Reader reader = new InputStreamReader(inputStream);
                PrintStream printStream = new PrintStream(outputStream, true, CHARSET_NAME)
        ) {
            ASLParserConsumer parserConsumer = new ASLParserConsumer(new Context(null), printStream);
            new asl.input.ASLParser(new asl.input.ASLLexer(reader), parserConsumer).parse();
            outputData = outputStream.toString(CHARSET_NAME);
        } catch (Exception e) {
            Assert.fail("Exception on input: " + e.getMessage());
        }

        String expectedOutputData = null;
        InputStream referenceInputStream = classLoader.getResourceAsStream(OUTPUT_FILE);
        Assert.assertNotNull("File with expected output not found!", referenceInputStream);
        try (Reader reader = new InputStreamReader(referenceInputStream)) {
            expectedOutputData = new BufferedReader(reader).lines().collect(Collectors.joining(SYSTEM_DELIMITER));
        } catch (Exception e) {
            Assert.fail("Exception on expected output reading: " + e.getMessage());
        }

        Assert.assertEquals(expectedOutputData, outputData);
    }
}
