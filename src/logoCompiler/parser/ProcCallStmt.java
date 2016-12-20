package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.IdentToken;
import logoCompiler.lexer.LParenToken;
import logoCompiler.lexer.RParenToken;

import java.io.PrintWriter;

public final class ProcCallStmt extends Stmt {
    private String parentProcArgName;
    private String name;
    private Expr expr;

    public ProcCallStmt(String name, Expr expr,String parentProcArgName) {
        this.parentProcArgName = parentProcArgName;
        this.name = name;
        this.expr = expr;
    }

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if (!(parser.getCurrentToken() instanceof IdentToken)) {
            ErrorLogger.logUnexpectedToken(IdentToken.getTokenName(),parser.getCurrentToken());
        }
        String name = ((IdentToken)parser.getCurrentToken()).getAttr();
        parser.getNextToken();

        if (!(parser.getCurrentToken() instanceof LParenToken)) {
            ErrorLogger.logUnexpectedToken(LParenToken.getTokenName(), parser.getCurrentToken());
        }
        parser.getNextToken();

        if(parser.getCurrentToken() instanceof LParenToken) {
            ErrorLogger.logParsingError(parser.getCurrentToken(),"Extra \"(\" in procedure call.");
        }

        Expr expr = Expr.parse(parser, parentProcArgName);

        if (!(parser.getCurrentToken() instanceof RParenToken)) {
            ErrorLogger.logUnexpectedToken(RParenToken.getTokenName(), parser.getCurrentToken());
        }
        parser.getNextToken();

        if(parser.getCurrentToken() instanceof RParenToken) {
            ErrorLogger.logParsingError(parser.getCurrentToken(),"Extra \")\" in procedure call.");
        }

        return new ProcCallStmt(name,expr,parentProcArgName);
    }
    @Override
    public void codegen(PrintWriter output) {
        if(!parentProcArgName.equals("VOID")) {
            output.println(parentProcArgName);
        }
        expr.codegen(output);
        output.println(name);
        if(!parentProcArgName.equals("VOID")) {
            output.println("/" + parentProcArgName + " exch def");
        }
    }
}
