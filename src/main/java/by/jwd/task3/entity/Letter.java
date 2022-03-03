package by.jwd.task3.entity;

import by.jwd.task3.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Letter implements TextComponent {
    static Logger logger = LogManager.getLogger();

    private char letter;

    public Letter(char letter) {
        this.letter = letter;
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
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + letter;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Letter other = (Letter) obj;
        if (letter != other.letter) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }
}
