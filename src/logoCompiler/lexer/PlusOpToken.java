package logoCompiler.lexer;

public class PlusOpToken extends OperatorToken {

    public PlusOpToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String psOpCode() {
        return "add";
    }

    @Override
    public int precedence() {
        return 3;
    }

    @Override
    public String toString() {
        return "+";
    }

}
