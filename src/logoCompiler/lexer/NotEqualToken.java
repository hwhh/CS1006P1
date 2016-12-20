package logoCompiler.lexer;

public class NotEqualToken extends OperatorToken {

    public NotEqualToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.BOOLEAN_OPERATOR};
    }

    @Override
    public String psOpCode() {return "ne";}

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return "!=";
    }

}
