package by.jwd.task3.interpreter;

import java.util.List;

public class Client {
    private Context context = new Context();
    public double handleExpression(List<MathExpression> expression) {
        expression.forEach(t -> t.interpret(context));
        return context.popValue();
    }
}
