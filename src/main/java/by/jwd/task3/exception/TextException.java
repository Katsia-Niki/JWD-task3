package by.jwd.task3.exception;

public class TextException extends Exception {
    public TextException() {
        super();
    }

    public TextException (String message) {
        super(message);
    }

    public TextException (Exception e) {
        super(e);
    }

    public TextException(String message, Exception e) {
        super(message, e);
    }
}
