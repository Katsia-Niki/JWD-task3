package by.jwd.task3.entity;

public enum TextComponentType {
    TEXT("",""),
    PHARAGRAPH("\t", "\n"),
    SENTENCE("", ""),
    LEXEM("", " "),
    WORD("", ""),
    LETTER("", ""),
    PUNCTUATION("", "");

    private final String prefix;
    private final String postfix;

    TextComponentType(String prefix, String postfix){
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
