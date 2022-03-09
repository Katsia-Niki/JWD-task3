package by.jwd.task3.parser;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;

import java.util.List;
import java.util.stream.Stream;

public class ParagraphParser extends AbstractTextParser {

    private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";

    public ParagraphParser() {
        this.nextParser = new SentenceParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        List<String> paragraphs = Stream.of(data.split(PARAGRAPH_SPLITTER_REGEX))
                .filter(p -> !p.isEmpty())
                .toList();

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            nextParser.parse(paragraphComponent, paragraph);
        }
    }
}
