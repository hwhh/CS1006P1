package logoCompiler.lexer;

public class RESTOREToken extends Token {
    public RESTOREToken(int lineCount) {
        super(lineCount);
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING};
    }

    @Override
    public String toString() {
        return "RESTORE";
    }
}
