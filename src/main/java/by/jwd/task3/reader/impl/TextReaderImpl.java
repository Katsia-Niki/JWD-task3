package by.jwd.task3.reader.impl;

import by.jwd.task3.exception.TextException;
import by.jwd.task3.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    static Logger logger = LogManager.getLogger();


    @Override
    public String readText(String pathToFile) throws TextException {

        Path path = Paths.get(pathToFile);
        if (!Files.exists(path)) {
            logger.error("File " + pathToFile + " not found.");
            throw new TextException("File " + pathToFile + " not found.");
        }

        String text = "";
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            logger.error("File " + pathToFile + " can't be open.", e);
            throw new TextException("File " + pathToFile + " can't be open.", e);
        }
        return text;
    }
}
