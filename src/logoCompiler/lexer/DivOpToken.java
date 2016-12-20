package logoCompiler.lexer;

public class DivOpToken extends OperatorToken {

    public DivOpToken(int lineCount) {
        super(lineCount);
    }

    @Override
    public String psOpCode() {
        return "div";
    }

    @Override
    public int precedence() {
        return 2;
    }

    public static String getTokenName() {
        return "/";
    }

    @Override
    public String toString() {
        return "/";
    }

}