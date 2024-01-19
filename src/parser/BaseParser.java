package parser;


import operations.Expression;

public class BaseParser implements TripleParser {
    protected static final char END = '\0';
    protected final StringExpression source;
    protected char ch = ' ';

    protected BaseParser(final StringExpression source) {
        this.source = source;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    protected boolean take(final String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (!take(c)) {
                return false;
            }
        }
        return true;
    }

    protected boolean test(final String value) { // if take(value) then take it else return
        source.savePos();
        if (take(value)) {
            return true;
        }
        source.returnToSavedPos();
        return false;
    }

    protected IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(source.getPos() + ": " + message);
    }

    protected boolean eof() {
        return take(END);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    @Override
    public Expression parse(String expression) {
        return null;
    }
}