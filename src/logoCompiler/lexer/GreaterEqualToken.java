package logoCompiler.lexer;

public class GreaterEqualToken extends OperatorToken {


    public GreaterEqualToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.BOOLEAN_OPERATOR};
    }

    @Override
    public String psOpCode() {
        return "ge";
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return ">=";
    }

}
