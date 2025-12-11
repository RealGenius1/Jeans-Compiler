package src;

public class Token {
    public enum Type {
        JACKET, JEGGING, VEST, JORT, SEW, BUTTON_UP, BUTTON_DOWN, ZIP_UP, ZIP_DOWN,
        DISCOUNT, DESIGN, CATWALK, EVERYTHING_MUST_GO, ADVERTISE, PAIR, EOF,

        IDENTIFIER, STRING, INTEGER, DOUBLE, PLUS, MINUS, TIMES, DIVIDE, KEYWORD, INVALID,
        ASSIGNMENT_OPERATOR, SEMICOLON, DOT, LEFT_BRACE, RIGHT_BRACE,
        PRINT, CONCAT, NEW, CHARACTER, MAIN
    }

    public final Type type;
    public final String text;

    public Token(Type type, String text){
        this.type = type;
        this.text = text;
    }

    public String toString(){
        return type + "(" + text + ")";
    }
}
