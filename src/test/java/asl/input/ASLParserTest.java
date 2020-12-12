package asl.input;

import java_cup.runtime.ComplexSymbolFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ASLParserTest {
    private static final Path TEST_FOLDER = Path.of("src/test/resources/data");

//    @Test
//    public void creationTest() {
//        ASLParser p = null;
//        try (Reader reader = new InputStreamReader(System.in)) {
//            var sf = new ComplexSymbolFactory();
//            p = new ASLParser(new ASLLexer(reader, sf), sf, new ASLParserConsumer());
//        } catch (Exception ignore) {
//        }
//        Assert.assertNotNull("Parser was not created!", p);
//    }

    @Test
    public void parseTests() {
        Assert.assertTrue("Test directory not found!", Files.isDirectory(TEST_FOLDER));
        try (Stream<Path> testPaths = Files.walk(TEST_FOLDER)) {
            testPaths.forEach(testPath -> {
                
            });
        } catch (IOException ignore) {
            Assert.fail("Error during parser tests!");
        }
    }
}