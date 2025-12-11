package src;

import java.util.*;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input){
        this.input = input;
    }
    private void skipWhiteSpace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
    public boolean matchWord(String w){
        skipWhiteSpace();
        if(input.startsWith(w, pos)){
            pos += w.length();
            return true;
        } else {
            return false;
        }
    }
    public Token next() {
        skipWhiteSpace();
        if(pos >= input.length()){
            return new Token(Token.Type.EOF, "");
        }
        String[][] keywords = {};
        for(String[] kw : keywords){

        }

    }

}
