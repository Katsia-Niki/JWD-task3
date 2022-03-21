package by.jwd.task3.entity;

import by.jwd.task3.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArithmeticExprResult implements TextComponent {
    static Logger logger = LogManager.getLogger();

    private double result;
    private TextComponentType type = TextComponentType.ARITHMETIC_EXPR_RESULT;

    public ArithmeticExprResult(double result, TextComponentType type) {
        this.result = result;
        this.type = type;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.error("Not supported operation to this component. ");
        return false;
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.error("Not supported operation to this component. ");
        return false;
    }

    @Override
    public List<TextComponent> getChildren() {
        logger.error("Not supported operation to this component. ");
        throw new UnsupportedOperationException("Not supported operation to this component. ");
    }

    @Override
    public TextComponent getChildByIndex(int index) {
        logger.error("Not supported operation to this component. ");
        throw new UnsupportedOperationException("Not supported operation to this component. ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArithmeticExprResult that = (ArithmeticExprResult) o;

        if (Double.compare(that.result, result) != 0) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        temp = Double.doubleToLongBits(result);
        result1 = (int) (temp ^ (temp >>> 32));
        result1 = 31 * result1 + (type != null ? type.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(type.getPrefix());
        sb.append(result);
        sb.append(type.getPostfix());
        return sb.toString();
    }
}
