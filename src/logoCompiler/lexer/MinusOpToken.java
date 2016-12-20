package logoCompiler.lexer;

public class MinusOpToken extends OperatorToken {

    public MinusOpToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String psOpCode() {return "sub";}

    @Override
    public int precedence() {
        return 3;
    }

    @Override
    public String toString() {
        return "-";
    }

}
