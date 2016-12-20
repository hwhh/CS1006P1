package logoCompiler.lexer;

import java.util.Arrays;

//Abstract class which all the token inherit from contains the appropriate code needed for error handling
public abstract class Token {
    private int lineNumber;

    //An array
    protected TokenTrait[] TRAITS = {};

    public int getLineNumber() {
        return lineNumber;
    }

    public Token(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public abstract String toString();

    public boolean hasTokenTrait(TokenTrait type) {
        return Arrays.asList(TRAITS).contains(type);
    }

    public int precedence() {
        //set precedence of all non-operators to 0
        //Override this for Operators
        return 0;
    }
}
