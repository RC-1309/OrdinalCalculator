package operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ordinals.NormalFormat;
import ordinals.Term;

public abstract class BinaryOperation implements Expression {
    protected final Expression firstExpression;
    protected final Expression secondExpression;

    public BinaryOperation(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    protected abstract String getOperation();

    protected abstract NormalFormat calculate(final NormalFormat left, final NormalFormat right);

    @Override
    public boolean specialCommutative() {
        return false;
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString() + " " + getOperation() + " " + secondExpression.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperation that = (BinaryOperation) o;
        return firstExpression.equals(that.firstExpression) && secondExpression.equals(that.secondExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstExpression, secondExpression, getClass());
    }

    private static void makeExpression(StringBuilder sb, String expression, boolean needsBrackets) {
        if (needsBrackets) {
            sb.append("(").append(expression).append(")");
        } else {
            sb.append(expression);
        }
    }

    @Override
    public boolean getAssociative() {
        return true;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        makeExpression(sb, firstExpression.toMiniString(), firstExpression.getPriority() > getPriority());
        sb.append(" ").append(getOperation()).append(" ");
        makeExpression(sb, secondExpression.toMiniString(),
                !(uniqueCommutative() && secondExpression.uniqueCommutative() && getClass() == secondExpression.getClass())
                        && (secondExpression.getPriority() > getPriority() || secondExpression.getPriority() == getPriority()
                        && (!getCommutative() || secondExpression.specialCommutative())));
        return sb.toString();
    }

    @Override
    public boolean uniqueCommutative() {
        return false;
    }

    public NormalFormat evaluate() {
        return calculate(firstExpression.evaluate(), secondExpression.evaluate());
    }
}
