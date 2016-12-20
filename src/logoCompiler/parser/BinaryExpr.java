package logoCompiler.parser;

import logoCompiler.lexer.OperatorToken;

import java.io.PrintWriter;

/*
 * binary-expr:
 *   expr op expr
 *
 *   where op is one of '+',  '-',  '*', '/',
 *                      '==', '!=', '>', '<', '<=', '>='
 */
public final class BinaryExpr extends Expr {
    public Expr left;
    public OperatorToken oper;
    public Expr right;

    public BinaryExpr(Expr left, OperatorToken oper, Expr right) {
        this.left = left;
        this.oper = oper;
        this.right = right;
    }


    @Override
    public void codegen(PrintWriter output) {
        left.codegen(output);
        right.codegen(output);
        output.println(oper.psOpCode());
    }
}
