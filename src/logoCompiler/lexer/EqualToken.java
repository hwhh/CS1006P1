package logoCompiler.lexer;

import java.util.Arrays;

public class EqualToken extends OperatorToken {

    public static final TokenTrait[] TYPES = {TokenTrait.BOOLEAN_OPERATOR};

    public EqualToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public String psOpCode() {
        return "eq";
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public String toString() {
        return "==";
    }

    @Override
    public boolean hasTokenTrait(TokenTrait type) {
        return Arrays.asList(TYPES).contains(type);
    }
}
