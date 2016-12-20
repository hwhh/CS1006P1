package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.*;

import java.io.PrintWriter;

public abstract class Stmt {

    public static Stmt parse(Parser parser, String parentProcArgName) throws ParsingException {
        if(parser.getCurrentToken() instanceof FORWARDToken) {
            return ForwardStmt.parse(parser, parentProcArgName);
        }

        if(parser.getCurrentToken() instanceof LEFTToken) {
            return LeftStmt.parse(parser, parentProcArgName);
        }

        if(parser.getCurrentToken() instanceof RIGHTToken) {
            return RightStmt.parse(parser, parentProcArgName);
        }

        if(parser.getCurrentToken() instanceof IFToken) {
            return IfStmt.parse(parser, parentProcArgName);
        }

        if(parser.getCurrentToken() instanceof IdentToken) {
            return ProcCallStmt.parse(parser, parentProcArgName);
        }
        if(parser.getCurrentToken() instanceof SAVEToken) {
            return SaveStateStmt.parse(parser,parentProcArgName);
        }
        if(parser.getCurrentToken() instanceof RESTOREToken) {
            return RestoreStateStmt.parse(parser,parentProcArgName);
        }

        ErrorLogger.logUnexpectedToken("statement", parser.getCurrentToken());
        return new ErrorStmt();
    }

    public abstract void codegen(PrintWriter output);
}

