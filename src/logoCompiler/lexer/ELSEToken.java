package logoCompiler.lexer;

public class ELSEToken extends Token {

    public ELSEToken(int lineNumber) {
        super(lineNumber);
        TRAITS = new TokenTrait[]{TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName() {
        return "ELSE";
    }

    @Override
    public String toString() {
        return "ELSE";
    }
}
