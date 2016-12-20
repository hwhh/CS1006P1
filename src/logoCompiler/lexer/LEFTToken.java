package logoCompiler.lexer;

public class LEFTToken extends Token {
    public LEFTToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING, TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName() {
        return "LEFT";
    }

    @Override
    public String toString() {
        return "LEFT";
    }

}
