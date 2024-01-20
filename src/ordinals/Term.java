package ordinals;

import java.util.Objects;

public class Term {
    private final NormalFormat degree;
    private final int cnst;

    public Term(NormalFormat degree, int aConst) {
        this.degree = degree;
        cnst = aConst;
    }

    public Term(Term term) {
        degree = new NormalFormat(term.degree);
        cnst = term.cnst;
    }

    public NormalFormat getDegree() {
        return degree;
    }

    public int getConst() {
        return cnst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term that = (Term) o;
        return that.cnst == cnst && Objects.equals(that.degree, degree);
    }

    public boolean greatDegree(Term other) {
        if (other.degree == null && degree != null) {
            return true;
        }
        if (other.degree != null && degree == null) {
            return false;
        }
        if (degree == other.degree || degree == null && other.degree == null) {
            return false;
        }
        return degree.great(other.degree);
    }

    public boolean great(Term other) {
        if (other.degree == null && degree != null) {
            return true;
        }
        if (other.degree != null && degree == null) {
            return false;
        }
        if (degree == other.degree || degree == null && other.degree == null) {
            return cnst > other.cnst;
        }
        return degree.great(other.degree);
    }

    @Override
    public String toString() {
        return "(" + (degree == null ? "" : "w ^ " + degree + " * ") + cnst + ")";
    }
}
