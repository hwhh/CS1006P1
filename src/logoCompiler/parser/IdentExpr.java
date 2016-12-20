package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.IdentToken;

import java.io.PrintWriter;

public final class IdentExpr extends Expr {
    String name;

    public IdentExpr(String name) {
        this.name = name;
    }

    public static Expr parse(Parser parser, String parentProcArgName) throws ParsingException {
        String name = ((IdentToken)parser.getCurrentToken()).getAttr();
        if(!parentProcArgName.equals("%ERROR%") && !name.equals(parentProcArgName)) {
            ErrorLogger.logParsingError(parser.getCurrentToken(),"Argument \"" + name + "\" not in scope");
        }
        parser.getNextToken();
        return new IdentExpr(name);
    }

    @Override
    public void codegen(PrintWriter output) {
        if(!name.equals("VOID"))
            output.println(name);
    }
}
