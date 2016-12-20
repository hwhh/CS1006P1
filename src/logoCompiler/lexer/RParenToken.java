package logoCompiler.lexer;

public class RParenToken extends Token {

    public RParenToken(int lineNumber) {
        super(lineNumber);
    }

    public static String getTokenName(){
        return ")";
    }

    @Override
    public String toString() {
        return ")";
    }

}
