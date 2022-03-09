package by.jwd.task3.service.impl;

import by.jwd.task3.entity.*;
import by.jwd.task3.exception.TextException;
import by.jwd.task3.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    static Logger logger = LogManager.getLogger();

    @Override
    public List<TextComponent> sortParagraphBySentenceCount(TextComponent text) throws TextException {

        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }
        List<TextComponent> paragraphs = text.getChildren();
        paragraphs.sort(new Comparator<TextComponent>() {
            @Override
            public int compare(TextComponent one, TextComponent other) {
                Integer sizeOne = one.getChildren().size();
                Integer sizeOther = other.getChildren().size();
                return sizeOne.compareTo(sizeOther);
            }
        });
        return paragraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws TextException {
        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }

        List<TextComponent> sentences = text.getChildren().stream().
                flatMap(c -> c.getChildren().stream())
                .toList();
        OptionalInt optionalMaxLengthWord = sentences.stream()
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> (!(w instanceof Punctuation) && !(w instanceof ArithmeticExprResult)))
                .mapToInt(w -> w.getChildren().size())
                .max();
        int maxLengthWord = optionalMaxLengthWord.orElseThrow(() -> new TextException("Incorrect data."));

        List<TextComponent> sentencesWithMaxLengthWord = sentences.stream()
                .filter(s -> s.getChildren().stream()
                        .anyMatch(l -> l.getChildren().stream()
                                .filter(w -> (!(w instanceof Punctuation) && !(w instanceof ArithmeticExprResult)))
                                .anyMatch(w -> w.getChildren().size() == maxLengthWord)))
                .toList();

        return sentencesWithMaxLengthWord;
    }

    @Override
    public void removeSentencesWithWordsLessThan(TextComponent text, int minCount) throws TextException {
        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int wordCount = 0;
                for (TextComponent lexeme : sentence.getChildren()) {
                    if (!(lexeme instanceof Punctuation) && !(lexeme instanceof ArithmeticExprResult)) {
                        wordCount++;
                    }
                }
                if (wordCount < minCount) {
                    paragraph.remove(sentence);
                }
            }
        }
    }


    @Override
    public Map<String, Integer> findAndCountSameWords(TextComponent text) throws TextException {
        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }

        Map<String, Integer> sameWords = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> (!(w instanceof Punctuation) && !(w instanceof ArithmeticExprResult)))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, (i1, i2) -> i1 + i2));

        sameWords.values().removeIf(i -> i == 1);

        return sameWords;
    }

    @Override
    public long countConsonants(TextComponent text) {
        return 0;
    }

    @Override
    public long countVowels(TextComponent text) {
        return 0;
    }

    private boolean validateParameter(TextComponent parameter) {
        if (!(parameter instanceof TextComposite)) {
            return false;
        }
        TextComposite composite = (TextComposite) parameter;
        if (composite.getType() != TextComponentType.TEXT) {
            return false;
        }
        return true;
    }
}
