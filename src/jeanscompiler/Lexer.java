package jeanscompiler;

import java.util.*;
import jeanscompiler.Token.*;

public class Lexer {
    private final String input;             // This is the input from the file
    private int pos = 0;                    // This is the pointer to the char we are at

//    This is a map of keywords and their corresponding Token Types
Map<String, Type> KEYWORDS = Map.ofEntries(
        Map.entry("jacket", Type.JACKET),
        Map.entry("jegging", Type.JEGGING),
        Map.entry("vest", Type.VEST),
        Map.entry("jort", Type.JORT),
        Map.entry("hat", Type.HAT),

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
        Map.entry("pair", Type.PAIR),

        Map.entry("wash", Type.WASH),
        Map.entry("pocket", Type.POCKET),
        Map.entry("measure", Type.MEASURE),
        Map.entry("loan", Type.LOAN),
        Map.entry("bankrupt", Type.BANKRUPT),
        Map.entry("stitch", Type.STITCH),
        Map.entry("shipping", Type.SHIPPING),
        Map.entry("liquidate", Type.LIQUIDATE),
        Map.entry("budget", Type.BUDGET),
        Map.entry("buy", Type.BUY),
        Map.entry("sell", Type.SELL),
        Map.entry("borrow", Type.BORROW),
        Map.entry("order", Type.ORDER),
        Map.entry("listing", Type.LISTING),

        // Boolean literals
        Map.entry("true", Type.TRUE),
        Map.entry("false", Type.FALSE),

        // Language keywords
        Map.entry("if", Type.IF),
        Map.entry("else", Type.ELSE),
        Map.entry("<", Type.LESS_THAN),
        Map.entry(">", Type.GREATER_THAN),
        Map.entry("list", Type.LIST),
        Map.entry("denim", Type.DENIM),
        Map.entry("wear", Type.WEAR),
        Map.entry("hang", Type.HANG),
        Map.entry("closet", Type.CLOSET),
        Map.entry("outfit", Type.OUTFIT),
        Map.entry("jog", Type.JOG),
        Map.entry("until", Type.UNTIL),
        Map.entry("tear", Type.TEAR),
        Map.entry("hem", Type.HEM),
        Map.entry("boring", Type.BORING),
        Map.entry("jeansEmergency", Type.JEANS_EMERGENCY),
        Map.entry("thrift", Type.THRIFT),
        Map.entry("model", Type.MODEL),
        Map.entry("and", Type.AND),
        Map.entry("or", Type.OR),
        Map.entry("!=", Type.NEQUAL),
        Map.entry("for", Type.FOR),
        Map.entry("operaticJeanPhrases", Type.OPERATIC_JEAN_PHRASES),

        // Misc token types
        Map.entry("identifier", Type.IDENTIFIER),
        Map.entry("string", Type.STRING),
        Map.entry("integer", Type.INTEGER),
        Map.entry("double", Type.DOUBLE),
        Map.entry("plus", Type.PLUS),
        Map.entry("minus", Type.MINUS),
        Map.entry("times", Type.TIMES),
        Map.entry("divide", Type.DIVIDE),
        Map.entry("keyword", Type.KEYWORD),
        Map.entry("invalid", Type.INVALID),

        Map.entry("assign", Type.ASSIGNMENT_OPERATOR),
        Map.entry("semicolon", Type.SEMICOLON),
        Map.entry("dot", Type.DOT),
        Map.entry("leftBrace", Type.LEFT_BRACE),
        Map.entry("rightBrace", Type.RIGHT_BRACE),
        Map.entry("lparen", Type.LPAREN),
        Map.entry("rparen", Type.RPAREN),

        Map.entry("print", Type.PRINT),
        Map.entry("concat", Type.CONCAT),
        Map.entry("new", Type.NEW),
        Map.entry("character", Type.CHARACTER),
        Map.entry("main", Type.MAIN),
        Map.entry("closeQuote", Type.CLOSE_QUOTE),
        Map.entry("modulo", Type.MODULO),
        Map.entry("boolean", Type.BOOLEAN),

        Map.entry("eof", Type.EOF)
);

    /**
     * Function that returns the char at pointer without moving the pointer
     * @return char at the pointer
     */
    private char peek() {
        return pos < input.length() ? input.charAt(pos) : '\0';
    }

    /**
     * Function to return the char at the pointer and advance it
     * @return the char at the pointer and then advance the pointer
     */
    private char advance() {
        return input.charAt(pos++);
    }

    /**
     * Constructor
     * @param input the input string of the file
     */
    public Lexer(String input){
        this.input = input;
    }

    /**
     * Skip ahead through all the white space
     */
    private void skipWhiteSpace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }

    /**
     * Matches the current input pointers with a given keyword
     * @param w The keyword we are matching the input with
     * @return True if input matches the word, false otherwise
     */
    public boolean matchWord(String w){
        skipWhiteSpace();
        if(input.startsWith(w, pos)){
            pos += w.length();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This Tokenizes the current symbol and adds it to the list
     * @return The token of the current symbol
     */
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
            if(input.charAt(pos) == 46){
                pos++;
                while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                    pos++;
                return new Token(Type.DOUBLE, input.substring(start, pos));
            }
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
        if(peek() == 39){
            pos++;
            String c = input.substring(pos, pos+1);
            pos++;
            pos++;
            return new Token(Type.CHARACTER, c);
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
