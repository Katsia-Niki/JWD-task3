package by.jwd.task3.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolishNotationCalculator {
    private static PolishNotationCalculator instance = new PolishNotationCalculator();
    public static PolishNotationCalculator getInstance() { return instance; }

    private PolishNotationCalculator() {}


    public List<MathExpression> calculate(String polishNotation) {
        List<MathExpression> expression = new ArrayList<>();
        Arrays.asList(polishNotation.split("\\p{Blank}+")).forEach(token -> {
            switch (token.charAt(0)) {
                case MathOperation.MINUS:
                    expression.add(c -> c.pushValue(-c.popValue() + c.popValue()));
                    break;
                case MathOperation.PLUS:
                    expression.add(c -> c.pushValue(c.popValue() + c.popValue()));
                    break;
                case MathOperation.DIVIDE:
                    expression.add(c -> c.pushValue(1 / c.popValue() * c.popValue()));
                    break;
                case MathOperation.MULTIPLY:
                    expression.add(c -> c.pushValue(c.popValue() * c.popValue()));
                    break;
                case MathOperation.UNARY_MINUS:
                    expression.add(c -> c.pushValue(-c.popValue()));
                    break;
                default:
                    expression.add(c -> c.pushValue(Double.parseDouble(token)));
            }
        });
        return expression;
    }
}
