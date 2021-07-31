package asl.input;

import asl.model.system.Context;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ASLInputFromFileTest {
    private static final String INPUT_FILE = "data/TestInput.asl";
    private static final String OUTPUT_FILE = "data/ExpectedOutput";
    private static final String CHARSET_NAME = UTF_8.name();
    private static final String SYSTEM_DELIMITER = "\r\n";

    @Test
    public void readFile() throws URISyntaxException {
        String outputData = null;
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            try (PrintStream printStream = new PrintStream(outputStream, true, CHARSET_NAME)) {
                URL inputFile = classLoader.getResource(INPUT_FILE);
                Assert.assertNotNull("File with expected input not found!", inputFile);
                var applicationOptions = ApplicationOptions.builder()
                        .setInputPrefix("> ")
                        .setOutput(printStream)
                        .setDebug(true)
                        .setSourcePath(Paths.get(inputFile.toURI()))
                        .build();

                var emptyContext = new Context(null);
                ASLConsumer aslConsumer = new ConsumerBuilder(applicationOptions)
                        .setContext(emptyContext)
                        .build();

                new SourceBasedApplication(emptyContext, applicationOptions, new ResettableExecutor(aslConsumer)).run();
                outputData = outputStream.toString(CHARSET_NAME);
            }
        } catch (IOException e) {
            Assert.fail("Exception on input: " + e.getMessage());
        }

        String expectedOutputData = null;
        try (InputStream referenceInputStream = classLoader.getResourceAsStream(OUTPUT_FILE)) {
            Assert.assertNotNull("File with expected output not found!", referenceInputStream);
            try (Reader reader = new InputStreamReader(referenceInputStream)) {
                expectedOutputData = new BufferedReader(reader).lines().collect(Collectors.joining(SYSTEM_DELIMITER));
            }
        } catch (Exception e) {
            Assert.fail("Exception on expected output reading: " + e.getMessage());
        }

        Assert.assertEquals(expectedOutputData, outputData);
    }
}
