package ordinals;

import operations.Expression;

public class Normalizer {
    public NormalFormat normalize(Expression expression) {
        return expression.evaluate();
    }
}
