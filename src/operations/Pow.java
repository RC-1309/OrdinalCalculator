package operations;

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
    protected int calculate(int a, int b) {
        return 0;
    }

    @Override
    protected double calculate(double a, double b) {
        return 0;
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
