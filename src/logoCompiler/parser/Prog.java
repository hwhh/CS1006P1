package logoCompiler.parser;

import logoCompiler.ErrorLogger;
import logoCompiler.lexer.EOIToken;
import logoCompiler.lexer.PROCToken;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 * prog:
 *   { proc }
 */
public final class Prog {

    List<Proc> procs;

    public Prog(List<Proc> procs) {
        this.procs = procs;
    }

    public static Prog parse(Parser parser) throws ParsingException {

        List<Proc> procs = new ArrayList<>();

        while (!(parser.getCurrentToken() instanceof EOIToken)) {
            procs.add(Proc.parse(parser));
        }
        if (parser.getCurrentToken() instanceof EOIToken) {
            parser.getNextToken();
        } else {

            ErrorLogger.logUnexpectedToken(EOIToken.getTokenName(),parser.getCurrentToken());
        }
        return new Prog(procs);
    }

    public void codegen(PrintWriter output) {
        for (Proc proc : procs) {
            proc.codegen(output);
        }
    }
}
