package logoCompiler.parser;

import java.io.PrintWriter;

public class RestoreStateStmt extends Stmt {

    public static Stmt parse(Parser parser, String parentProcArgName) {
        parser.getNextToken();
        return new RestoreStateStmt();
    }
    @Override
    public void codegen(PrintWriter output) {
        output.println("ResetState");
    }
}
