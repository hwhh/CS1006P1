package logoCompiler.parser;

import java.io.PrintWriter;

public class SaveStateStmt extends Stmt {

    public static Stmt parse(Parser parser, String parentProcArgName) {
        parser.getNextToken();
        return new SaveStateStmt();
    }
    @Override
    public void codegen(PrintWriter output) {
        output.println("SaveState");
    }
}
