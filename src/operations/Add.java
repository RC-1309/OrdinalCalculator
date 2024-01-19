package operations;

public class Add extends BinaryOperation {
    private static final String OPERATION = "+";

    public Add(Expression firstExpression, Expression secondExpression) {
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
    protected int calculate(int a, int b) {
        return a + b;
    }

    @Override
    public int getPriority() {
        return Priority.getAddPriority();
    }

    @Override
    protected double calculate(double a, double b) {
        return a + b;
    }
}
