package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.OperatorToken;

import java.io.PrintWriter;

/*
 * expr:
 *   primary-expr 
 *   binary-expr 
 */
public abstract class Expr {

    public static Expr parse(Parser parser, String parentProcArgName) throws ParsingException {
        return fraserHanson(1, parser,parentProcArgName);
    }

    //Binary Expressions precedence handler from Fraser and Hanson C Compiler book
    private static Expr fraserHanson(int k, Parser parser, String parentProcArgName) throws ParsingException {
        int i;
        Expr left;
        OperatorToken oper;
        Expr right;
        left = PrimaryExpr.parse(parser, parentProcArgName);


        for (i = parser.getCurrentToken().precedence(); i >= k; i--) {
            while (parser.getCurrentToken().precedence() == i) {
                if(!(parser.getCurrentToken() instanceof OperatorToken)) {
                    ErrorLogger.logUnexpectedToken(OperatorToken.getTokenName(),parser.getCurrentToken());
                }
                oper = (OperatorToken) parser.getCurrentToken();
                parser.getNextToken();
                right = fraserHanson(i + 1, parser, parentProcArgName);
                left = new BinaryExpr(left, oper, right);
            }
        }
        return left;
    }

    public abstract void codegen(PrintWriter output);
}
