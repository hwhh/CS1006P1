package logoCompiler.lexer;

public class LessThanToken extends OperatorToken {

    public LessThanToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.BOOLEAN_OPERATOR};
    }

    @Override
    public String psOpCode() {
        return "lt";
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return "<";
    }

}
