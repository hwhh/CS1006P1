package logoCompiler.lexer;

import logoCompiler.lexer.Token;

public class ErrorToken extends Token {
    private final String value;

    public ErrorToken(int lineNumber, String value) {
        super(lineNumber);
        this.value = value;
    }
    public ErrorToken(int lineNumber) {
        this(lineNumber,"Error");
    }
    @Override
    public String toString() {
        return value;
    }
}
