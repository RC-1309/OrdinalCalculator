package operations;

public interface Expression {
    int getPriority();
    // 0 for operations.UnaryOperation, operations.Const and operations.Variable
    // 1 for operations.pow
    // 2 for operations.Multiply and operations.Divide
    // 3 for operations.Subtract and operations.Add

    boolean specialCommutative();
    // true for operations.Divide
    // false for another

    boolean getCommutative();
    // true: operations.Const, operations.Variable, operations.Add and operations.Multiply
    // false: Unary, operations.Divide and operations.Subtract

    boolean uniqueCommutative();
    // false: another

    boolean getAssociative();
    // true: left
    // false: right

    String toMiniString();
}
