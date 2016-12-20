package logoCompiler.parser;

import logoCompiler.lexer.NumToken;

import java.io.PrintWriter;

public final class NumExpr extends Expr {

    public int value;

    public NumExpr(int value) {
        this.value = value;
    }

    public static Expr parse(Parser parser) {
        if (parser.getCurrentToken() instanceof NumToken){
            int value = ((NumToken)parser.getCurrentToken()).getValue();
            parser.getNextToken();
            return new NumExpr(value);
        }
        else {
            return new ErrorExpr();
        }
    }
    @Override
    public void codegen(PrintWriter output) {
        output.println(value);
    }
}
