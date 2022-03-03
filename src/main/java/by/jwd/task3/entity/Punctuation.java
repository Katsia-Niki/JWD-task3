package by.jwd.task3.entity;

import by.jwd.task3.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Punctuation implements TextComponent {
    static Logger logger = LogManager.getLogger();

    private char punctuation;

    public Punctuation(char punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.error("Not supported operation to this component. ");
        return false;
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.error("Not supported operation to this component. ");
        return false;
    }

    @Override
    public List<TextComponent> getChildren() throws TextException {
        logger.error("Not supported operation to this component. ");
        throw new TextException("Not supported operation to this component. ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Punctuation that = (Punctuation) o;

        return punctuation == that.punctuation;
    }

    @Override
    public int hashCode() {
        return punctuation;
    }
}
