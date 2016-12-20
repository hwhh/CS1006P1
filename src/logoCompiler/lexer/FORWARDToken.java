package logoCompiler.lexer;

public class FORWARDToken extends Token {

    public FORWARDToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING, TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName(){
        return "FORWARD";
    }

    @Override
    public String toString() {
        return "FORWARD";
    }

}
