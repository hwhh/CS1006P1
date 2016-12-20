package logoCompiler.lexer;


import logoCompiler.ErrorLogger;

import java.io.FileInputStream;
import java.io.IOException;

public final class Lexer {
    private FileInputStream input;
    int lineCount = 1;
    private int currentCharacter = ' ';

    public Lexer(FileInputStream input) {
        this.input = input;
    }

    private void stepCharacter()  {
        //Keep creating tokens until the lexer reaches end of the file
        try {
            if(this.currentCharacter != -1)
                //set the current car to the next char in the input file
                this.currentCharacter = input.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDigit(int ch){
        return (ch>='0' && ch <='9');
    }

    private static boolean isLowerCaseChar(int ch) {
        return ('a' <= ch && ch <= 'z');
    }

    private static boolean isUpperCaseChar(int ch) {
        return ('A' <= ch && ch <= 'Z');
    }

    private void skipWhitespace() {
        //skip the white space
        while (currentCharacter == ' '|| currentCharacter == '\t' || currentCharacter == '\n' || currentCharacter == '\r') {
            if(currentCharacter == '\n'){
                // Every new line char, increment lineCount variable, used to display line where error has occurred
                lineCount++;
            }
            stepCharacter();
        }
    }

    public Token getNextToken() {
        skipWhitespace();

        if( isLowerCaseChar(currentCharacter) || isUpperCaseChar(currentCharacter) ) { //if the current car is in range a-z or A-Z
            String s = "";
            //do loop to find entire string built from chars
            do {
                s += (char) currentCharacter; // cast the current char to actual chat, then add to string
                stepCharacter();
            } while(isLowerCaseChar(currentCharacter) || isUpperCaseChar(currentCharacter) || isDigit(currentCharacter));
            //Once string is created, find what the statement is and return correct new instance of a token class
            switch(s.toUpperCase()){
                case "PROC":{
                    return new PROCToken(lineCount);
                }
                case "FORWARD":{
                    return new FORWARDToken(lineCount);
                }
                case "LEFT":{
                    return new LEFTToken(lineCount);
                }
                case "RIGHT":{
                    return new RIGHTToken(lineCount);
                }
                case "IF":{
                    return new IFToken(lineCount);
                }
                case "THEN":{
                    return new THENToken(lineCount);
                }
                case "ELSE":{
                    return new ELSEToken(lineCount);
                }
                case "ENDIF":{
                    return new ENDIFToken(lineCount);
                }
                case "SAVE": {
                    return new SAVEToken(lineCount);
                }
                case "RESTORE": {
                    return new RESTOREToken(lineCount);
                }
                default: {
                    //if the string made does not contain digits and is not a recognised token, it is an identifier
                    return new IdentToken(lineCount,s);
                }
            }
        }

        if (isDigit(currentCharacter)) {
            String i = "";
            //Build a string containing digits
            while (isDigit(currentCharacter)) {
                i += (char) currentCharacter;
                stepCharacter();
            }
            //check to see if a number with multiple digits starts with a 0, if so bad number
            if(!(i.equals("0") || !i.startsWith("0"))) {
                ErrorLogger.logError(lineCount," Numbers cannot start with 0");
            }
            //Return a new instance of the NumToken class
            return new NumToken(lineCount,Integer.parseInt(i));
        }

        //identify new character and return correct token
        switch (currentCharacter) {
            case -1: {
                return new EOIToken(lineCount);
            }
            case '(': {
                stepCharacter();
                return new LParenToken(lineCount);
            }
            case ')': {
                stepCharacter();
                return new RParenToken(lineCount);
            }
            case '+':{
                stepCharacter();
                return new PlusOpToken(lineCount);
            }
            case '-':{
                stepCharacter();
                return new MinusOpToken(lineCount);
            }
            case '*': {
                stepCharacter();
                return new MultOpToken(lineCount);
            }
            case '/': {
                stepCharacter();
                return new DivOpToken(lineCount);
            }
            case '=': {
                stepCharacter();
                if(currentCharacter == '=') {
                    stepCharacter();
                    return new EqualToken(lineCount);
                }
                return new ErrorToken(lineCount,"=");
            }
            case '!': {
                stepCharacter();
                if(currentCharacter == '=') {
                    stepCharacter();
                    return new NotEqualToken(lineCount);
                }
                return new ErrorToken(lineCount,"!");
            }
            case '>': {
                stepCharacter();
                if(currentCharacter == '=') {
                    stepCharacter();
                    return new GreaterEqualToken(lineCount);
                }
                return new GreaterThanToken(lineCount);
            }
            case '<': {
                stepCharacter();
                if(currentCharacter == '=') {
                    stepCharacter();
                    return new LessEqualToken(lineCount);
                }
                return new LessThanToken(lineCount);
            }
            default:
                //if default case met, the string created contains is invalid
                ErrorLogger.logUnexpectedCharacter(lineCount,(char) currentCharacter);
                stepCharacter();
                return getNextToken();
        }
    }
}
