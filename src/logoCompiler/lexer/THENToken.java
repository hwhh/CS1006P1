package logoCompiler.lexer;

public class THENToken extends Token{


    public THENToken(int lineNumber) {
        super(lineNumber);
    }

    public static String getTokenName(){
        return "THEN";
    }

    @Override
    public String toString() {
        return "THEN";
    }

}
