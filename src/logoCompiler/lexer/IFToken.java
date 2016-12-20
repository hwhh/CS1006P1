package logoCompiler.lexer;

public class IFToken extends Token {
    public IFToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING, TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName() {
        return "IF";
    }

    @Override
    public String toString() {
        return "IF";
    }
}
