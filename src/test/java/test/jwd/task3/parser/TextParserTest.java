package test.jwd.task3.parser;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;
import by.jwd.task3.parser.AbstractTextParser;
import by.jwd.task3.parser.ParagraphParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextParserTest {
    @Test
    public void parseTest() {
        String text = "It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged. It was popularised in the 5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. \n" +
                "It is a (7+5*12*(2+5-2-71))/12 established fact that a reader will be of a page when looking at its layout. \n" +
                "Bye. \n";

        TextComponent textComponent = new TextComposite(TextComponentType.TEXT);
        AbstractTextParser parser = new ParagraphParser();
        parser.parse(textComponent, text);

        String actual = textComponent.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -8.0 essentially 7.7142857142857135 unchanged. It was popularised in the -873.3293064876957 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using -9.567701863354037 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English. \n" +
                "\tIt is a -329.41666666666663 established fact that a reader will be of a page when looking at its layout. \n" +
                "\tBye. \n";
        assertEquals(actual, expected);
    }
}
