package logoCompiler.lexer;

public class ENDIFToken extends Token {

    public ENDIFToken(int lineNumber) {
        super(lineNumber);
    }

    public static String getTokenName() {
        return "ENDIF";
    }

    @Override
    public String toString() {
        return "ENDIF";
    }

    @Override
    public boolean hasTokenTrait(TokenTrait type) {
        return false;
    }
}
