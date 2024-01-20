package operations;

import ordinals.Evaluator;
import ordinals.NormalFormat;
import ordinals.Term;

import java.util.ArrayList;
import java.util.List;

public class Multiply extends BinaryOperation {
    private static final String OPERATION = "*";

    public Multiply(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    protected NormalFormat calculate(final NormalFormat left, final NormalFormat right) {
        return Evaluator.mul(left, right);
    }

    @Override
    public int getPriority() {
        return Priority.getMultiplyPriority();
    }
}
