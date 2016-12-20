package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.*;

import java.io.PrintWriter;


/*
IF condition THEN
    statements
ELSE
    statements
ENDIF
 */
public final class IfStmt extends Stmt{
    Expr condition;
    Stmts ifStmts;
    Stmts elseStmts;

    public IfStmt(Expr condition, Stmts ifStmts, Stmts elseStmts) {
        this.condition = condition;
        this.ifStmts = ifStmts;
        this.elseStmts = elseStmts;
    }

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if(!(parser.getCurrentToken() instanceof IFToken)) {
            ErrorLogger.logUnexpectedToken(IFToken.getTokenName(),parser.getCurrentToken());
        }
        Token initialIFToken = parser.getCurrentToken();
        parser.getNextToken();

        Expr condition = Expr.parse(parser, parentProcArgName);

        if(condition instanceof BinaryExpr) {
            Token operator = ((BinaryExpr) condition).oper;
            if (!operator.hasTokenTrait(TokenTrait.BOOLEAN_OPERATOR)) {
                ErrorLogger.logParsingError(operator,"Expected a boolean operator in if condition");
            }
        }
        else {
            ErrorLogger.logParsingError(initialIFToken,"Expected a binary expression in if condition");
        }

        if(parser.getCurrentToken() instanceof THENToken) {
            parser.getNextToken();
        }
        else {
                ErrorLogger.logUnexpectedToken(THENToken.getTokenName(), parser.getCurrentToken());
        }

        Stmts ifStmts = Stmts.parse(parser, parentProcArgName);

        if(parser.getCurrentToken() instanceof ELSEToken) {
            parser.getNextToken();
        }
        else {
            ErrorLogger.logUnexpectedToken(ELSEToken.getTokenName(), parser.getCurrentToken());
        }

        Stmts elseStmts = Stmts.parse(parser, parentProcArgName);

        if(parser.getCurrentToken() instanceof ENDIFToken) {
            parser.getNextToken();
        }
        else {
            ErrorLogger.logUnexpectedToken(ENDIFToken.getTokenName(), parser.getCurrentToken());
        }


        return new IfStmt(condition,ifStmts,elseStmts);
    }

    @Override
    public void codegen(PrintWriter output) {
        condition.codegen(output);
        output.println("{");
        ifStmts.codegen(output);
        output.println("} {");
        elseStmts.codegen(output);
        output.println("} ifelse");
    }
}
