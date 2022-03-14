package by.jwd.task3.parser;

import by.jwd.task3.entity.ArithmeticExprResult;
import by.jwd.task3.entity.TextComponent;
import by.jwd.task3.entity.TextComponentType;
import by.jwd.task3.entity.TextComposite;
import by.jwd.task3.interpreter.Context;
import by.jwd.task3.interpreter.MathExpression;
import by.jwd.task3.interpreter.PolishNotationCalculator;
import by.jwd.task3.polishnotation.ExpressionParser;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class LexemeAndExpressionParser extends AbstractTextParser {

    private static final String LEXEME_SPLITTER_REGEX = "\\s";
    private static final String EXPRESSION_REGEX = "([0-9]+[\\+\\-\\*\\/]{1}[0-9]+)+([\\+\\-\\*\\/]{1}[0-9]+)*";

    public LexemeAndExpressionParser() {
        this.nextParser = new WordAndPunctuationParser();
    }

    @Override
    public void parse(TextComponent component, String data) {

        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        Pattern pattern = Pattern.compile(EXPRESSION_REGEX);

        for(int i = 0; i < lexemes.length; i++) {
            if(pattern.matcher(lexemes[i]).find()) {
                lexemes[i] = calculate(lexemes[i]);
                TextComponent lexemeComponent = new ArithmeticExprResult(Double.parseDouble(lexemes[i]), TextComponentType.ARITHMETIC_EXPR_RESULT);
                component.add(lexemeComponent);

            } else {
                TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
                component.add(lexemeComponent);
                nextParser.parse(lexemeComponent, lexemes[i]);
            }
        }
    }

    private String calculate(String expr) {
        List<String> expressions = ExpressionParser.parse(expr);

        StringJoiner exprString = new StringJoiner(" ");
        for (String e : expressions) {
            exprString.add(e);
        }

        String polishNotationExpr = exprString.toString();
        String result = "";
        if (ExpressionParser.flag) {
            PolishNotationCalculator calculator = PolishNotationCalculator.getInstance();

            List<MathExpression> mathExpressions = calculator.calculate(polishNotationExpr);
            Context context = new Context();
            mathExpressions.forEach(t -> t.interpret(context));
            result = String.valueOf(context.popValue());
        }
        return result;
    }
}
