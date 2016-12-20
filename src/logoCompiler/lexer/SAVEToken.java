package logoCompiler.lexer;

public class SAVEToken extends Token{
    public SAVEToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING};
    }

    @Override
    public String toString() {
        return "SAVE";
    }
}
