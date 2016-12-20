package logoCompiler.lexer;

public class MultOpToken extends OperatorToken {

    public MultOpToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String psOpCode() {
        return "mul";
    }

    @Override
    public int precedence() {
        return 2;
    }

    @Override
    public String toString() {
        return "*";
    }

}
