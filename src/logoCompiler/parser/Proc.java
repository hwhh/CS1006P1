package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.*;

import java.io.PrintWriter;

/*
 * proc:
 *   "PROC" ident '(' ident ')' stmts 
 */
public final class Proc {

    String name;
    String arg;
    Stmts stmts;

    public Proc(String name, String arg, Stmts stmts) {
        this.name = name;
        this.arg = arg;
        this.stmts = stmts;
    }

    public static Proc parse(Parser parser) throws ParsingException {
        String name = null;
        String arg = null;

        if(parser.getCurrentToken() instanceof PROCToken) {
            parser.getNextToken();
        }
        else {
            ErrorLogger.logUnexpectedToken(PROCToken.getTokenName(),parser.getCurrentToken());
            while(!(parser.getCurrentToken() instanceof PROCToken) && !(parser.getCurrentToken() instanceof EOIToken)) {
                parser.getNextToken();
            }
            parser.getNextToken();
        }

        if (parser.getCurrentToken() instanceof IdentToken) {
            name = ((IdentToken) parser.getCurrentToken()).getAttr();
            parser.getNextToken();
        } else {
            ErrorLogger.logUnexpectedToken(IdentToken.getTokenName(), parser.getCurrentToken());
        }

        if (parser.getCurrentToken() instanceof LParenToken) {
            parser.getNextToken();
        } else {
            ErrorLogger.logUnexpectedToken(LParenToken.getTokenName(), parser.getCurrentToken());
        }

        if (parser.getCurrentToken() instanceof IdentToken) {
            arg = ((IdentToken) parser.getCurrentToken()).getAttr();
            parser.getNextToken();
        } else {
            if(parser.getCurrentToken() instanceof LParenToken) {
                ErrorLogger.logParsingError(parser.getCurrentToken(), "Extra \"(\" in function declaration");
            }
            else {
                ErrorLogger.logUnexpectedToken(IdentToken.getTokenName(), parser.getCurrentToken());
            }
        }

        if (parser.getCurrentToken() instanceof RParenToken) {
            parser.getNextToken();
        } else {
            ErrorLogger.logUnexpectedToken(RParenToken.getTokenName(), parser.getCurrentToken());
        }

        Stmts stmts = Stmts.parse(parser,arg);

        return new Proc(name, arg, stmts);
    }

    public void codegen(PrintWriter output) {
        output.print("/");
        output.print(name);
        output.println(" {");
        if(!arg.equals("VOID")) {
            output.println("/" + arg + " exch def");
        }
        stmts.codegen(output);
        if(!arg.equals("VOID")) {
        }
        output.println("} def");
        output.println();
    }
}
