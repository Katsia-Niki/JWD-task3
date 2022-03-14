package by.jwd.task3.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues.pop();
    }

    public void pushValue(Double value) {
        this.contextValues.push(value);
    }
}
