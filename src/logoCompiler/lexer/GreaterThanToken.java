package logoCompiler.lexer;

public class GreaterThanToken extends OperatorToken {

    public GreaterThanToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.BOOLEAN_OPERATOR};
    }

    @Override
    public String psOpCode() {
        return "gt";
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return ">";
    }

}
