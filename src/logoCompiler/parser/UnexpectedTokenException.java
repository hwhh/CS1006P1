package logoCompiler.parser;

import logoCompiler.lexer.Token;

/*
    In <class name / code block> :
        expecting <expected token> but got <found token/ wrong token>


 */
public class UnexpectedTokenException extends ParsingException {
    String message;

    public UnexpectedTokenException(String expectedToken, Token foundToken) {
        this(foundToken.getLineNumber(),foundToken.toString(),expectedToken);
    }

    public UnexpectedTokenException(int lineNo, String foundToken, String expectedToken) {

        this.message = "Error at line number: "+lineNo+" Expected " + expectedToken + " but got " + foundToken;
    }



    @Override
    public String getMessage() {
        return message;
    }
}
