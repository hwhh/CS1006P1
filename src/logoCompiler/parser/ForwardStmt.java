package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.FORWARDToken;

import java.io.PrintWriter;

/*
 *   "FORWARD" expr
 */
public final class ForwardStmt extends Stmt {
    Expr expr;

    public ForwardStmt(Expr expr) {
        this.expr = expr;
    }

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if(!(parser.getCurrentToken() instanceof FORWARDToken)) {
            ErrorLogger.logUnexpectedToken(FORWARDToken.getTokenName(),parser.getCurrentToken());
        }
        parser.getNextToken();
        Expr expr = Expr.parse(parser,parentProcArgName);
        return new ForwardStmt(expr);
    }

    @Override
    public void codegen(PrintWriter output) {
        expr.codegen(output);
        output.println("Forward");
    }
}
