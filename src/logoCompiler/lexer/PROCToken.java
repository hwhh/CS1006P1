package logoCompiler.lexer;

public final class PROCToken extends Token {

    public PROCToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName() {
        return "PROC";
    }

    @Override
    public String toString() {
        return "PROC";
    }

}
