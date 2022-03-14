package by.jwd.task3.service;

import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComposite;
import by.jwd.task3.exception.TextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphBySentenceCount(TextComponent text) throws TextException;

    List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws TextException;

    void removeSentencesWithWordsLessThan(TextComponent text, int minCount) throws TextException;

    Map<String, Integer> findAndCountSameWords(TextComponent text) throws TextException;

    long countConsonants(TextComponent text) throws TextException;

    long countVowels(TextComponent text) throws TextException;
}
