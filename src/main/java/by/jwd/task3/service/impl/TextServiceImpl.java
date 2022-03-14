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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    static Logger logger = LogManager.getLogger();

    private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";
    private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";

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
                .filter(l -> !(l instanceof  ArithmeticExprResult))
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> !(w instanceof Punctuation))
                .mapToInt(w -> w.getChildren().size())
                .max();
        int maxLengthWord = optionalMaxLengthWord.orElseThrow(() -> new TextException("Incorrect data."));

        List<TextComponent> sentencesWithMaxLengthWord = sentences.stream()
                .filter(s -> s.getChildren().stream()
                        .filter(l -> !(l instanceof  ArithmeticExprResult))
                        .anyMatch(l -> l.getChildren().stream()
                                .filter(w -> (!(w instanceof Punctuation)))
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
                    if (!(lexeme instanceof ArithmeticExprResult)) {
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
                .filter(l -> !(l instanceof  ArithmeticExprResult))
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> !(w instanceof Punctuation))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));

        sameWords.values().removeIf(i -> i == 1);

        return sameWords;
    }

    @Override
    public long countConsonants(TextComponent text) throws TextException {
        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);

        long counter = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .filter(l -> !(l instanceof  ArithmeticExprResult))
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> !(w instanceof Punctuation))
                .flatMap(w -> w.getChildren().stream())
                .map(let -> let.toString())
                .filter(let -> pattern.matcher(let).matches())
                .count();
        return counter;
    }

    @Override
    public long countVowels(TextComponent text) throws TextException {
        if (!(validateParameter(text))) {
            logger.error("Given component is not text.");
            throw new TextException("Given component is not text.");
        }
        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        long counter = text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .filter(l -> !(l instanceof  ArithmeticExprResult))
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> !(w instanceof Punctuation))
                .flatMap(w -> w.getChildren().stream())
                .map(let -> let.toString())
                .filter(let -> pattern.matcher(let).matches())
                .count();
        return counter;
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
