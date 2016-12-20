package logoCompiler.lexer;

//End of Input Token
public final class EOIToken extends Token {
    public EOIToken(int lineNumber) {
        super(lineNumber);
        this.TRAITS = new TokenTrait[] {TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName() {
        return "end of file";
    }

    @Override
    public String toString() {
        return "end of file";
    }

}
