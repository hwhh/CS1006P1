package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.IdentToken;
import logoCompiler.lexer.NumToken;

/*
 * primary-expr:
 *   num
 *   ident
 */
public abstract class PrimaryExpr extends Expr {
    public static Expr parse(Parser parser, String parentProcArgName) throws ParsingException {
        if (parser.getCurrentToken() instanceof NumToken) {
            return NumExpr.parse(parser);
        } else if (parser.getCurrentToken() instanceof IdentToken) {
            return IdentExpr.parse(parser,parentProcArgName);
        } else {
            ErrorLogger.logUnexpectedToken("number or identifier", parser.getCurrentToken());
            return new ErrorExpr();
        }
    }
}
