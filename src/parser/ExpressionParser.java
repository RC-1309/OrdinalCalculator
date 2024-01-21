package parser;
import operations.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser implements TripleParser {
    @Override
    public Expression parse(String expression) {
//        System.err.println(expression);
        return new Parser(new StringExpression(expression)).parse();
    }

    public static class Parser extends BaseParser {
        private static final int MAX_PRIORITY = Priority.getMaxPriority();

        protected Parser(StringExpression source) {
            super(source);
        }

        private Expression create() {
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            if (Character.isDigit(ch)) {
                while (Character.isDigit(ch)) {
                    sb.append(take());
                }
                return new Const(Integer.parseInt(sb.toString()));
            } else {
                while (Character.isLetter(ch)) {
                    sb.append(take());
                }
                return new Variable(sb.toString());
            }
        }

        private boolean isOpenBracket() {
            skipWhitespace();
            return take('(');
        }

        private boolean isCloseBracket() {
            skipWhitespace();
            return take(')');
        }

        public Expression parse() {
            Expression expression = parseStringToExpression();
            if (eof()) {
                return expression;
            } else {
                throw new IllegalArgumentException("some mistake");
            }
        }

        public Expression parseStringToExpression() {
            return allPriority(MAX_PRIORITY);
        }


        public static Expression getExpression(String operand, Expression left, Expression right) {
            if ("+".equals(operand)) {
                return new Add(left, right);
            } else if ("*".equals(operand)) {
                return new Multiply(left, right);
            } else if ("^".equals(operand)) {
                return new Pow(left, right);
            }
            return null;
        }

        public Expression allPriority(int priority) {
            if (priority == 0) {
                return priorityZero();
            }
            String[] nameOperand = Priority.PRIORITIES.get(priority);
            List<Expression> expressionList = new ArrayList<>();
            expressionList.add(allPriority(priority - 1));
            boolean hasNextExpression = true;
            while (hasNextExpression) {
                hasNextExpression = false;
                skipWhitespace();
                for (String operand : nameOperand) {
                    while (test(operand)) {
                        expressionList.add(allPriority(priority - 1));
                        hasNextExpression = true;
                    }
                    Expression expression;
                    if (getExpression(operand, null, null).getAssociative()) {
                        expression = expressionList.get(0);
                        for (int i = 1; i < expressionList.size(); i++) {
                            expression = getExpression(operand, expression, expressionList.get(i));
                        }
                    } else {
                        expression = expressionList.get(expressionList.size() - 1);
                        for (int i = expressionList.size() - 2; i >= 0; i--) {
                            expression = getExpression(operand, expressionList.get(i), expression);
                        }
                    }
                    expressionList.clear();
                    expressionList.add(expression);
                }
            }
            return expressionList.get(0);
        }

        public Expression priorityZero() {
            skipWhitespace();
            if (between('0', '9') || between('w', 'w')) {
                return create();
            } else if (isOpenBracket()) {
                Expression cur = allPriority(MAX_PRIORITY);
                if (isCloseBracket()) {
                    return cur;
                } else {
                    throw error("Cannot find close bracket");
                }
            } else {
                throw error("This operation not supported");
            }
        }
    }
}


