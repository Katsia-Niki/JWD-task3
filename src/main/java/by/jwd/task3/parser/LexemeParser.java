package by.jwd.task3.parser;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;

public class LexemeParser extends AbstractTextParser {

    private static final String LEXEME_SPLITTER_REGEX = "\\s";

    public LexemeParser() {
        this.nextParser = new WordAndPunctuationParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for(String lexeme : lexemes) {
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);
            nextParser.parse(lexemeComponent, lexeme);
        }
    }
}
