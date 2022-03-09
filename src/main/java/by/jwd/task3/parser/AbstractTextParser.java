package by.jwd.task3.parser;

import by.jwd.task3.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser {
    static Logger logger = LogManager.getLogger();

    protected AbstractTextParser nextParser = DefaultTextParser.getInstance();

    public abstract void parse(TextComponent component, String data);

    private static class DefaultTextParser extends AbstractTextParser {
        private static DefaultTextParser defaultParser = new DefaultTextParser();

        public static DefaultTextParser getInstance() {
            return defaultParser;
        }

        @Override
        public void parse(TextComponent component, String data) {
            logger.info("End of parsers chain.");
        }
    }
}
