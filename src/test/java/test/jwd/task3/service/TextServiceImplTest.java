package test.jwd.task3.service;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;
import by.jwd.task3.exception.TextException;
import by.jwd.task3.parser.AbstractTextParser;
import by.jwd.task3.parser.ParagraphParser;
import by.jwd.task3.service.TextService;
import by.jwd.task3.service.impl.TextServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class TextServiceImplTest {

    private TextService service;
    private AbstractTextParser parser;

    @BeforeClass
    public void setUp() {
        service = new TextServiceImpl();
        parser = new ParagraphParser();

    }

    @Test
    public void sortParagraphBySentenceCountTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);
        List<TextComponent> actual = service.sortParagraphBySentenceCount(text);

        List<TextComponent> expected = new ArrayList<>();
        expected.add(text.getChildByIndex(1));
        expected.add(text.getChildByIndex(0));
        expected.add(text.getChildByIndex(3));
        expected.add(text.getChildByIndex(2));

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void sortParagraphBySentenceCountTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        TextComponent oneSentence = text.getChildByIndex(1);

        service.sortParagraphBySentenceCount(oneSentence);
    }

    @Test
    public void findSentencesWithLongestWordTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence with programmingLongestWord. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programmingLongestWord. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        List<TextComponent> actual = service.findSentencesWithLongestWord(text);
        List<TextComponent> expected = new ArrayList<>();
        expected.add(0, text.getChildByIndex(1).getChildByIndex(0));
        expected.add(1, text.getChildByIndex(3).getChildByIndex(0));

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void findSentencesWithLongestWordTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        TextComponent oneSentence = text.getChildByIndex(1);
        service.findSentencesWithLongestWord(oneSentence);
    }

    @Test
    public void removeSentencesWithWordsLessThanTest() throws TextException {
        String textAsString = "\tHello. Hello, world, hello!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! See you later. \n" +
                "\tI love java programming. Some text here. You read text here.";

        TextComponent expected = new TextComposite(TextComponentType.TEXT);
        parser.parse(expected, textAsString);
        TextComponent sentenceToRemove1 = expected.getChildByIndex(1).getChildByIndex(0);
        TextComponent sentenceToRemove2 = expected.getChildByIndex(0).getChildByIndex(0);
        expected.getChildByIndex(1).remove(sentenceToRemove1);
        expected.getChildByIndex(0).remove(sentenceToRemove2);

        TextComponent actual = new TextComposite(TextComponentType.TEXT);
        parser.parse(actual, textAsString);
        service.removeSentencesWithWordsLessThan(actual, 3);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void removeSentencesWithWordsLessThanTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        TextComponent oneSentence = text.getChildByIndex(1);
        service.removeSentencesWithWordsLessThan(oneSentence, 1);
    }

    @Test
    public void findAndCountSameWordsTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye, bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        Map<String, Integer> actual = service.findAndCountSameWords(text);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("hello", 2);
        expected.put("bye", 3);
        expected.put("text", 2);
        expected.put("here", 2);
        expected.put("you", 2);

        assertEqualsDeep(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void findAndCountSameWordsTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n" +
                "    One sentence. \n" +
                "\tHave a nice day! Bye. See you later. Bye-bye.\n" +
                "\tI love java programming. Some text here. You read text here.";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        TextComponent oneSentence = text.getChildByIndex(1);
        service.findAndCountSameWords(oneSentence);
    }

    @Test
    public void countVowelsInTextTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        long actual = service.countVowelsInText(text);
        long expected = 5L;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void countVowelsInTextTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);
        TextComponent oneSentence = text.getChildByIndex(0);
        service.countVowelsInText(oneSentence);
    }

    @Test
    public void countConsonantsInTextTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);

        long actual = service.countConsonantsInText(text);
        long expected = 10L;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = "Given component is not text.")
    public void countConsonantsInTextTestException() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);
        TextComponent oneSentence = text.getChildByIndex(0);
        service.countConsonantsInText(oneSentence);
    }

    @Test
    public void countConsonantsTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);
        TextComponent oneSentence = text.getChildByIndex(0).getChildByIndex(0);
        long actual = service.countConsonants(oneSentence);
        long expected = 3L;

        assertEquals(actual, expected);
    }

    @Test
    public void countVowelsTest() throws TextException {
        String textAsString = "\tHello. Hello, world!\n";
        TextComponent text = new TextComposite(TextComponentType.TEXT);
        parser.parse(text, textAsString);
        TextComponent oneSentence = text.getChildByIndex(0).getChildByIndex(0);
        long actual = service.countVowels(oneSentence);
        long expected = 2L;

        assertEquals(actual, expected);
    }

    @AfterClass
    public void tearDown() {
        service = null;
        parser = null;
    }
}