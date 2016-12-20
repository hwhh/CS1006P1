package logoCompiler.lexer;

public class LParenToken extends Token {

    public LParenToken(int lineNumber) {
        super(lineNumber);
    }

    public static String getTokenName(){return "(";}

    @Override
    public String toString() {
        return "(";
    }

}

