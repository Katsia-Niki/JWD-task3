package by.jwd.task3.entity;

import by.jwd.task3.exception.TextException;

import java.util.List;

public interface TextComponent {
    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    List<TextComponent> getChildren() throws TextException;
}
