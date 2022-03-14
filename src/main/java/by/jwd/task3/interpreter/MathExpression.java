package by.jwd.task3.interpreter;

import by.jwd.task3.interpreter.Context;

@FunctionalInterface
public interface MathExpression {
    void interpret(Context context);
}
