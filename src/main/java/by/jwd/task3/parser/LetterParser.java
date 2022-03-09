package by.jwd.task3.parser;

import by.jwd.task3.entity.Letter;
import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;

public class LetterParser extends AbstractTextParser {

    @Override
    public void parse(TextComponent component, String data) {
        char[] letters = data.toCharArray();

        for (char letter : letters) {
            component.add(new Letter(letter, TextComponentType.LETTER));
        }
    }
}
