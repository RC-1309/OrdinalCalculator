package parser;

import operations.Expression;

public interface TripleParser {
    Expression parse(String expression);
}
