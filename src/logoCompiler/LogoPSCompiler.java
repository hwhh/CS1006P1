package logoCompiler;

import logoCompiler.lexer.Lexer;
import logoCompiler.parser.Parser;
import logoCompiler.parser.Prog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogoPSCompiler {

    public static void main(String[] args) {
        Lexer lexer;
        String inputFile = "";
        String outputFile = "output.ps";


        switch(args.length) {
            case 0:
                ErrorLogger.logArgumentError("No arguments found");
                break;
            case 1:
                inputFile = args[0];
                break;
            case 2:
                inputFile = args[0];
                outputFile = args[1];
                break;
            default:
                ErrorLogger.logArgumentError("Found more than 2 arguments");
        }

        //Check if argument error flag is set, if its not continue
        if(!ErrorLogger.hasArgumentError()) {
            try {
                /*Create new lexer object, with the input file, then hand the
                parser the lexer object which contains the token,
                 */
                lexer = new Lexer(new FileInputStream(inputFile));
                Parser parser = new Parser(lexer);
                Prog prog = parser.parse();
                //Check if parsing error flag is set, if its not continue.
                if (!ErrorLogger.hasParsingError()) {
                    try {
                        //Create new output file
                        PrintWriter output = new PrintWriter(outputFile);
                        psPrologue(output);
                        prog.codegen(output);
                        psEpilogue(output);
                        output.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Compilation successful.");
                } else {
                    System.out.println();
                    System.out.println("Compilation failed.");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void psPrologue(PrintWriter output) {
        output.println("%!PS-Adobe-3.0");    // Adobe header
        /* rest of prologue ... */
        output.println("/Xpos    { 300 } def");
        output.println("/Ypos    { 500} def");
        output.println("/Heading { 0   } def");
        output.println("/Arg     { 0   } def");

        //Implementation of Right, Left and Forward procedures in PostScript
        output.println("/Right   {");
        output.println("Heading exch add Trueheading");
        output.println("/Heading exch def");
        output.println("} def");

        output.println("/Left {");
        output.println("Heading exch sub Trueheading");
        output.println("/Heading exch def");
        output.println("} def");

        output.println("/Trueheading {");
        output.println("360 mod dup");
        output.println("0 lt { 360 add } if");
        output.println("} def");

        output.println("/Forward {");
        output.println("dup  Heading sin mul");
        output.println("exch Heading cos mul");
        output.println("2 copy Newposition");
        output.println("rlineto");
        output.println("} def");

        // Save the current state of drawing
        output.println("/SaveState {");
        output.println("Xpos");
        output.println("Ypos");
        output.println("Heading");
        output.println("} def");

        // Restore the drawing state from the stack.
        output.println("/ResetState {");
        output.println("stroke");
        output.println("/Heading exch def");
        output.println("2 copy");
        output.println("/Ypos exch def");
        output.println("/Xpos exch def");
        output.println("newpath");
        output.println("moveto");

        output.println("} def");

        // Move position by x,y got from the stack.
        output.println("/Newposition {");
        output.println("Ypos add /Ypos exch def");
        output.println("Xpos add /Xpos exch def");
        output.println("} def");
        output.println();
    }

    public static void psEpilogue(PrintWriter output) {
	    /* epilogue ... */
        output.println();
        output.println("Xpos Ypos moveto");

        //Call Main to start
        output.println("MAIN");
        output.println("stroke");
        output.println("showpage");
    }
}
