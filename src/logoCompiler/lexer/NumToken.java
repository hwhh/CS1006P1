package logoCompiler.lexer;

public class NumToken extends Token{
    private final int num;

    public NumToken(int lineNumber, int num) {
        super(lineNumber);
        this.num = num;
    }

    public int getValue(){
        return num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

}
