package operations;

import ordinals.NormalFormat;
import ordinals.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Const implements Expression {
    private final int value;

    public Const(int value) {
        this.value = value;
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
    public boolean getCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return Priority.getConstAndVariablePriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getClass());
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public NormalFormat evaluate() {
        return new NormalFormat(List.of(new Term(null, value)));
    }

    @Override
    public boolean getAssociative() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return value == that.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
