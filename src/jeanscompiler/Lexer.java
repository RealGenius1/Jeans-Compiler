package jeanscompiler;

import java.util.*;
import jeanscompiler.Token.*;

public class Lexer {
    private final String input;
    private int pos = 0;
    Map<String, Type> KEYWORDS = Map.ofEntries(
            Map.entry("jacket", Type.JACKET),
            Map.entry("vest", Type.VEST),
            Map.entry("jegging", Type.JEGGING),
            Map.entry("jort", Type.JORT),
            Map.entry("sew", Type.SEW),
            Map.entry("buttonUp", Type.BUTTON_UP),
            Map.entry("buttonDown", Type.BUTTON_DOWN),
            Map.entry("zipUp", Type.ZIP_UP),
            Map.entry("zipDown", Type.ZIP_DOWN),
            Map.entry("discount", Type.DISCOUNT),
            Map.entry("design", Type.DESIGN),
            Map.entry("catwalk", Type.CATWALK),
            Map.entry("everythingMustGo", Type.EVERYTHING_MUST_GO),
            Map.entry("advertise", Type.ADVERTISE),
            Map.entry("pair", Type.PAIR)
    );

    private char peek() {
        return pos < input.length() ? input.charAt(pos) : '\0';
    }
    private char advance() {
        return input.charAt(pos++);
    }


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

        for (String key : KEYWORDS.keySet()) {
            if (input.startsWith(key, pos)) {
                pos += key.length();
                return new Token(KEYWORDS.get(key), key);
            }
        }

        if (Character.isDigit(input.charAt(pos))) {
            int start = pos;
            while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                pos++;
            return new Token(Token.Type.INTEGER, input.substring(start, pos));
        }
        if (Character.isLetter(input.charAt(pos))) {
            int start = pos;
            while (pos < input.length() && Character.isLetter(input.charAt(pos)))
                pos++;
            return new Token(Token.Type.IDENTIFIER, input.substring(start, pos));
        }
        // Match strings
        if (peek() == 8220 || peek() == 34) {
//            System.out.println("Found an open");
            pos++; // skip opening "
            int start = pos;
            while (pos < input.length() && (peek() != '"')) pos++;
            String str = input.substring(start, pos);
            pos++; // skip closing "
            return new Token(Token.Type.STRING, str);
        }

        // Match simple symbols
        switch (peek()) {
            case '+': pos++; return new Token(Token.Type.PLUS, "+");
            case '-': pos++; return new Token(Token.Type.MINUS, "-");
            case '*': pos++; return new Token(Token.Type.TIMES, "*");
            case '/': pos++; return new Token(Token.Type.DIVIDE, "/");
            case '(': pos++; return new Token(Token.Type.LPAREN, "(");
            case ')': pos++; return new Token(Token.Type.RPAREN, ")");
            case '{': pos++; return new Token(Type.LEFT_BRACE, "{");
            case '}': pos++; return new Token(Type.RIGHT_BRACE, "}");
            case ';': pos++; return new Token(Token.Type.SEMICOLON, ";");
            case '=': pos++; return new Token(Type.ASSIGNMENT_OPERATOR, "=");
//            case 34: pos++; return new Token(Type.CLOSE_QUOTE, "\"");
        }


        throw new RuntimeException("Unknown token at " + pos + " " + input.charAt(pos) + " - " + (int)(input.charAt(pos)));
    }

}
