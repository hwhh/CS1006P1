package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.LEFTToken;

import java.io.PrintWriter;

/*
 *   "LEFT" expr
 */
public final class LeftStmt extends Stmt {
    Expr expr;

    public LeftStmt(Expr expr) {
        this.expr = expr;
    }

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if(!(parser.getCurrentToken() instanceof LEFTToken)) {
            ErrorLogger.logUnexpectedToken(LEFTToken.getTokenName(), parser.getCurrentToken());
        }
        parser.getNextToken();
        Expr expr = Expr.parse(parser, parentProcArgName);
        return new LeftStmt(expr);
    }

    @Override
    public void codegen(PrintWriter output) {
        expr.codegen(output);
        output.println("Left");
    }
}
