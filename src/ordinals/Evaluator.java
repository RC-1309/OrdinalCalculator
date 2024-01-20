package ordinals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Evaluator {
    public static NormalFormat sum(NormalFormat leftForm, NormalFormat rightForm) {
        if (leftForm == null && rightForm == null) {
            return new NormalFormat(List.of());
        }
        if (leftForm == null) {
            return new NormalFormat(new ArrayList<>(rightForm.getCantorFormat()));
        }
        if (rightForm == null) {
            return new NormalFormat(new ArrayList<>(leftForm.getCantorFormat()));
        }
        List<Term> right = rightForm.getCantorFormat();
        List<Term> answer = new ArrayList<>(leftForm.getCantorFormat());
        while (!answer.isEmpty() && right.get(0).greatDegree(answer.get(answer.size() - 1))) {
            answer.remove(answer.size() - 1);
        }
        if (!answer.isEmpty() && Objects.equals(answer.get(answer.size() - 1).getDegree(), right.get(0).getDegree())) {
            answer.set(answer.size() - 1, new Term(right.get(0).getDegree(),
                    answer.get(answer.size() - 1).getConst() + right.get(0).getConst()));
            answer.addAll(right.subList(1, right.size()));
        } else {
            answer.addAll(right);
        }
        return new NormalFormat(answer);
    }

    private static NormalFormat mulTerm(final NormalFormat form, final Term term) {
        Term first = form.getCantorFormat().get(0);
        if (first.getConst() == 0 || term.getConst() == 0) {
            return new NormalFormat(List.of(new Term(null, 0)));
        }
        List<Term> answer;
        if (term.getDegree() == null) {
            answer = List.of(new Term(first.getDegree(), first.getConst() * term.getConst()));
        } else {
            answer = List.of(new Term(sum(first.getDegree(), term.getDegree()),  term.getConst()));
        }
        return new NormalFormat(answer);
    }

    public static NormalFormat mul(final NormalFormat leftForm, final NormalFormat rightForm) {
        List<NormalFormat> normalFormats = new ArrayList<>();
        for (Term t : rightForm.getCantorFormat()) {
            normalFormats.add(mulTerm(leftForm, t));
        }
        NormalFormat answer = normalFormats.get(0);
        for (int i = 1; i < normalFormats.size(); i++) {
            answer = sum(answer, normalFormats.get(i));
        }
        return answer;
    }

    private static NormalFormat powN(final NormalFormat form, int n) {
        NormalFormat answer = new NormalFormat(form);
        for (int i = 0; i < n - 1; i++) {
            answer = mul(answer, form);
        }
        return answer;
    }

    private static NormalFormat powTerm(final NormalFormat form, final Term term) {
        Term first = form.getCantorFormat().get(0);
        if (term.getConst() == 0) {
            return new NormalFormat(List.of(new Term(null, 1)));
        }
        NormalFormat answer;
        if (term.getDegree() == null) {
            answer = powN(form, term.getConst());
        } else {
            if (first.getDegree() == null) {
                if (first.getConst() == 1) {
                    answer = new NormalFormat(List.of(new Term(null, 1)));
                } else {
                    answer = new NormalFormat(List.of(new Term(new NormalFormat(List.of(new Term(term))), 1)));
                }
            } else {
                answer = new NormalFormat(List.of(new Term(mulTerm(first.getDegree(), term), 1)));
            }
        }
        return answer;
    }

    public static NormalFormat pow(final NormalFormat leftForm, final NormalFormat rightForm) {
        List<NormalFormat> normalFormats = new ArrayList<>();
        for (Term t : rightForm.getCantorFormat()) {
            normalFormats.add(powTerm(leftForm, t));
        }
        NormalFormat answer = normalFormats.get(0);
        for (int i = 1; i < normalFormats.size(); i++) {
            answer = mul(answer, normalFormats.get(i));
        }
        return answer;
    }
}
