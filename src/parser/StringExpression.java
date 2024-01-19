package parser;

public class StringExpression {
    private static final char END = '\0';
    private final String data;
    private int pos;
    private int savedPos = 0;

    public StringExpression(final String data) {
        this.data = data;
        pos = 0;
        savePos();
    }

    public boolean hasNext() {
        return pos < data.length();
    }

    public char next() {
        return data.charAt(pos++);
    }

    public void savePos() {
        savedPos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void returnToSavedPos() {
        pos = savedPos;
    }

    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
