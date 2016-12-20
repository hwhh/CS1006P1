package logoCompiler.lexer;

public class LessEqualToken extends OperatorToken {

    public LessEqualToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.BOOLEAN_OPERATOR};
    }

    @Override
    public String psOpCode() {
        return "le";
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return "<=";
    }

}
