package by.jwd.task3.parser;

import by.jwd.task3.entity.*;
import by.jwd.task3.interpreter.Client;
import by.jwd.task3.interpreter.MathExpression;
import by.jwd.task3.interpreter.PolishNotationCalculator;
import by.jwd.task3.polishnotation.ExpressionParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAndPunctuationParser extends AbstractTextParser {

    private static final String WORD_REGEX = "[a-zA-zа-яА-ЯёЁ]+(-[a-zA-Z]+)*";
    private static final String WORD_OR_PUNCTUATION_REGEX = "([a-zA-zа-яА-ЯёЁ]+(-[a-zA-Z]+)*)|([\\p{Punct}\u2026])";

    public WordAndPunctuationParser() {
        this.nextParser = new LetterParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String group = matcher.group();

            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(group);

            if (wordMatcher.matches()) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.add(wordComponent);
                nextParser.parse(wordComponent, group);
            } else {
                TextComponent punctuationComponent = new Punctuation(group.charAt(0), TextComponentType.PUNCTUATION);
                component.add(punctuationComponent);
            }
        }
    }
}
