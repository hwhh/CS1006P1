package logoCompiler.parser;


import logoCompiler.ErrorLogger;
import logoCompiler.lexer.ENDIFToken;

// This class is only used in error handling.
//  We use it to parse an ELSE <stmts> ENDIF portion of code.
public class ElseSyncStmt{

    public static void parse(Parser parser) throws ParsingException {
        parser.getNextToken();
        Stmts.parse(parser, "%ERROR%");
        if(!(parser.getCurrentToken() instanceof ENDIFToken)) {
            ErrorLogger.logUnexpectedToken(ENDIFToken.getTokenName(),parser.getCurrentToken());
        }
        parser.getCurrentToken();
    }
}
