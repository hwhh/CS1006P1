package logoCompiler;

import logoCompiler.lexer.Token;
import logoCompiler.parser.Parser;
import logoCompiler.parser.ParsingException;
import logoCompiler.parser.UnexpectedTokenException;

public  class ErrorLogger {
    private static boolean parsingError = false;
    private static boolean argumentError = false;


    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("First argument: input file");
        System.out.println("Second argument (optional): output file");
    }

    public static void logError(int lineNo, String message) {
        parsingError = true;
        System.out.println("Error at line " + lineNo + ": " + message);
    }

    public static void logParsingError(Token referenceToken, String message) throws ParsingException {
        logError(referenceToken.getLineNumber(),message);
        throw new ParsingException();
    }

    public static void logUnexpectedToken(int lineNo, String foundToken, String expectedToken) throws UnexpectedTokenException {
        logError(lineNo, "Expected " + expectedToken + " but got " + foundToken);
        throw new UnexpectedTokenException(lineNo, foundToken, expectedToken);
    }

    public static void logUnexpectedToken(String expectedToken, Token foundToken) throws UnexpectedTokenException {
        logUnexpectedToken(foundToken.getLineNumber(),foundToken.toString(),expectedToken);
    }


    public static void logUnexpectedCharacter(int lineNo, char ch) {
        parsingError = true;
        logError(lineNo,"Found unexpected character \"" + ch + "\"");
    }

    public static void logArgumentError(String message) {
        System.out.println("Invalid arguments: " + message);
        printUsage();
        argumentError = true;
    }

    public static boolean hasArgumentError(){
        return argumentError;
    }
    public static boolean hasParsingError(){
        return parsingError;
    }
}
