package by.jwd.task3.parser;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {

    private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).+?(\\.|\\?|\\!|\\u2026)(\\s|$)";

    public SentenceParser() {
        this.nextParser = new LexemeAndExpressionParser();
    }

    @Override
    public void parse(TextComponent component, String data) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String group = matcher.group();
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);
            nextParser.parse(sentenceComponent, group);
        }
    }
}
