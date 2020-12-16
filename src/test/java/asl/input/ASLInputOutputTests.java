package asl.input;

import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

public class ASLInputOutputTests {

    /**
     * @param testInput      входящая строка с выражениями
     * @param expectedOutput ожидаемая строка с результатами вывода вычисления выражений,
     *                       результаты выражений разделены переносом строки
     */
    public void test(String testInput, String expectedOutput) {
        try (Reader reader = new StringReader(testInput)) {
            TestConsumer testConsumer = new TestConsumer();
            new ASLParser(new ASLLexer(reader), testConsumer).parse();
            testConsumer.checkResult(expectedOutput);
        } catch (Exception e) {
            Assert.fail("Exception: " + e.getMessage());
        }
    }

    @Test
    public void oneIntegerAtomTest() {
        test("4;", "4");
    }

    @Test
    public void oneDoubleAtomTest() {
        test("1.2;", "1.2");
    }

    @Test
    public void booleanAtomsTest() {
        test("true;", "true");
        test("false;", "false");
    }

    @Test
    public void undefTest() {
        test("undef;", "undef");
    }

    @Test
    public void simpleQNameAtomTest() {
        test("testName;", "testName");
    }

    @Test
    public void undefVariableTest() {
        test("$x;", "undef");
    }

    @Test
    public void definedVariableTest() {
        test("$x = 5;", "5");
        test("$x = 5; $x;", "5\n5");
    }

    @Test
    public void redefinedVariableTest() {
        test("$x = 5; $y = $x; $x = 2; $y;", "5\n5\n2\n5");
    }

    @Test
    public void addIntegersTest() {
        test("2 + 3 + 4;", "9");
    }

    @Test
    public void addDoublesTest() {
        test("1.2 + 1.3 + 1.4;", "3.9");
    }

    @Test
    public void addIntegersAndDoubleTest() {
        test("2 + 3 + 1.4;", "6.4");
    }

    @Test
    public void conzEmptyTest() {
        test("conz();", "{}");
    }

    @Test
    public void conzTest() {
        test("conz(x1, 12, x2, 15);", "{\nx1 = 12,\nx2 = 15\n}");
    }

    @Test
    public void conzqTest() {
        test("$x = conz(a, 12); conzq($x, b, 4, a, 3);", "{\na = 12\n}\n{\nb = 4,\na = 3\n}");
    }
}
