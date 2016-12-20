package logoCompiler.lexer;

public abstract class OperatorToken extends Token {

    public OperatorToken(int lineNumber) {
        super(lineNumber);
    }

    public static String getTokenName(){
        return "operator";
    }

    public abstract String psOpCode();
}
