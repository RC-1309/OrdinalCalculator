package operations;

import ordinals.Evaluator;
import ordinals.NormalFormat;
import ordinals.Term;

import java.util.List;

public class Pow extends BinaryOperation {
    private static final String OPERATION = "^";

    public Pow(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean getAssociative() {
        return false;
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected NormalFormat calculate(final NormalFormat left, final NormalFormat right) {
        return Evaluator.pow(left, right);
    }

    @Override
    public int getPriority() {
        return Priority.getPowPriority();
    }

    @Override
    public boolean getCommutative() {
        return false;
    }
}
