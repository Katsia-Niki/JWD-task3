package by.jwd.task3.reader;

import by.jwd.task3.exception.TextException;

public interface TextReader {
    String readText(String pathToFile) throws TextException;
}
