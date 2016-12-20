package logoCompiler.lexer;

public class IdentToken extends Token {
    private final String name;

    public IdentToken(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING};
    }

    public String getAttr() {
        return name;
    }

    public static String getTokenName() {
        return "identifier";
    }

    @Override
    public String toString() {
        return name;
    }

}
