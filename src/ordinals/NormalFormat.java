package ordinals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.min;

public class NormalFormat {
    private final List<Term> cantorFormat;

    public NormalFormat(List<Term> list) {
        cantorFormat = list;
    }

    public NormalFormat(NormalFormat normalFormat) {
        cantorFormat = new ArrayList<>(normalFormat.getCantorFormat());
    }

    public List<Term> getCantorFormat() {
        return cantorFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFormat that = (NormalFormat) o;
        if (cantorFormat.size() != that.cantorFormat.size()) {
            return false;
        }
        for (int i = 0; i < cantorFormat.size(); i++) {
//            System.out.println(cantorFormat.get(i) + " " + that.cantorFormat.get(i));
            if (!Objects.equals(cantorFormat.get(i), that.cantorFormat.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean great(NormalFormat other) {
        if (other == null) {
            return true;
        }
        for (int i = 0; i < min(other.cantorFormat.size(), cantorFormat.size()); i++) {
            if (Objects.equals(cantorFormat.get(i), other.cantorFormat.get(i))) {
                continue;
            }
            return cantorFormat.get(i).great(other.cantorFormat.get(i));
        }
        return cantorFormat.size() > other.cantorFormat.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (!cantorFormat.isEmpty()) {
            sb.append(cantorFormat.get(0));
        }
        for (int i = 1; i < cantorFormat.size(); i++) {
            sb.append(" + ").append(cantorFormat.get(i));
        }
        return sb.append(")").toString();
    }
}
