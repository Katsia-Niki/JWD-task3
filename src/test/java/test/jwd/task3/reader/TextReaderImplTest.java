package test.jwd.task3.reader;

import by.jwd.task3.exception.TextException;
import by.jwd.task3.reader.TextReader;
import by.jwd.task3.reader.impl.TextReaderImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextReaderImplTest {
    private TextReader reader;

    @BeforeClass
    public void setUp() {
        reader = new TextReaderImpl();
    }

    @Test
    public void readTextTest() throws TextException {
        String pathToFile = "src\\test\\resources\\testData\\TestText.txt";
        String actual = reader.readText(pathToFile);
        String expected = "It has survived – not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged. It was popularised in the 5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\r\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\r\n" +
                "It is a (7+5*12*(2+5-2-71))/12 established fact that a reader will be of a page when looking at its layout.\r\n" +
                "Bye.";
        assertEquals(actual, expected);
    }

    @Test
    public void readTextTestEmpty() throws TextException {
        String pathToFile = "src\\test\\resources\\testData\\TestTextEmpty.txt";
        String actual = reader.readText(pathToFile);
        String expected = "";
        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class)
    public void readTextTestException() throws TextException {
        String pathToFile = "src\\test\\resources\\testData\\T.txt";
        reader.readText(pathToFile);
    }

    @AfterClass
    public void tearDown() {
        reader = null;
    }
}