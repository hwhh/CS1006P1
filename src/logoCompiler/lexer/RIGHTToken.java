package logoCompiler.lexer;

public class RIGHTToken extends Token {

    public RIGHTToken(int lineNumber) {
        super(lineNumber);

        TRAITS = new TokenTrait[]{TokenTrait.VALID_STATEMENT_BEGINNING, TokenTrait.SYNCHRONIZING_TOKEN};
    }

    public static String getTokenName(){
     return"RIGHTToken";
 }

    @Override
    public String toString() {
        return "RIGHT";
    }

}
