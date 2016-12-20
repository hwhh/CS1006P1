package logoCompiler.parser;

import logoCompiler.lexer.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Stmts extends Expr {

    List<Stmt> statements;

    public Stmts(List<Stmt> statements) {
        this.statements = statements;
    }

    public static boolean isValidStmtBeginning(Token t) {
        return t.hasTokenTrait(TokenTrait.VALID_STATEMENT_BEGINNING);
    }

    public static Stmts parse(Parser parser, String arg) throws ParsingException {
        ArrayList<Stmt> statements = new ArrayList<>();

        while(isValidStmtBeginning(parser.getCurrentToken())) {
            statements.add(Stmt.parse(parser,arg));
        }

        return new Stmts(statements);
    }

    @Override
    public void codegen(PrintWriter output) {
        for (Stmt statement : statements) {
            statement.codegen(output);
        }

    }
}
