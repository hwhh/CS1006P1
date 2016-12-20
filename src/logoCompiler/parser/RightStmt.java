package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.RIGHTToken;

import java.io.PrintWriter;

/*
 *   "RIGHT" expr
 */
public final class RightStmt extends Stmt {
    Expr expr;

    public RightStmt(Expr expr) {
        this.expr = expr;
    }

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if (!(parser.getCurrentToken() instanceof RIGHTToken)){
            ErrorLogger.logUnexpectedToken(RIGHTToken.getTokenName(),parser.getCurrentToken());

        }
        parser.getNextToken();
        Expr expr = Expr.parse(parser, parentProcArgName);
        return new RightStmt(expr);
    }

    @Override
    public void codegen(PrintWriter output) {
        expr.codegen(output);
        output.println("Right");
    }
}
