package operations;

import ordinals.NormalFormat;
import ordinals.Term;

import java.util.List;

public class Variable implements Expression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    @Override
    public boolean specialCommutative() {
        return false;
    }

    @Override
    public boolean uniqueCommutative() {
        return false;
    }

    @Override
    public int getPriority() {
        return Priority.getConstAndVariablePriority();
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable that = (Variable) o;
        return variable.equals(that.variable);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public NormalFormat evaluate() {
        return new NormalFormat(List.of(new Term(new NormalFormat(List.of(new Term(null, 1))), 1)));
    }

    @Override
    public boolean getAssociative() {
        return true;
    }
}
