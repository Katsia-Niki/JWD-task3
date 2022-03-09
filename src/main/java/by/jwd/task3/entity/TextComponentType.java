package by.jwd.task3.entity;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("\t", "\n"),
    SENTENCE,
    LEXEME("", " "),
    WORD,
    LETTER,
    PUNCTUATION,
    ARITHMETIC_EXPR_RESULT;

    private final String prefix;
    private final String postfix;

    TextComponentType(String prefix, String postfix){
        this.prefix = prefix;
        this.postfix = postfix;
    }

    TextComponentType() {
        prefix = "";
        postfix = "";
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
