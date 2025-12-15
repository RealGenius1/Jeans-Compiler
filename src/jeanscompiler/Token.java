package jeanscompiler;

public class Token {
    public enum Type {
        JACKET, JEGGING, VEST, JORT, HAT, SEW, BUTTON_UP, BUTTON_DOWN, ZIP_UP, ZIP_DOWN,
        DISCOUNT, DESIGN, CATWALK, EVERYTHING_MUST_GO, ADVERTISE, PAIR, EOF, WASH,
        POCKET, MEASURE, LOAN, BANKRUPT, STITCH, SHIPPING, LIQUIDATE, BUDGET, BUY, SELL, BORROW, ORDER, LISTING,
        IF, ELSE, ELIF, LESS_THAN, GREATER_THAN, LIST, DENIM, WEAR, HANG, CLOSET, OUTFIT, JOG, UNTIL,
        TEAR, HEM, BORING, JEANS_EMERGENCY, THRIFT, MODEL, AND, OR, NEQUAL, FOR, OPERATIC_JEAN_PHRASES,
        IDENTIFIER, STRING, INTEGER, DOUBLE, PLUS, MINUS, TIMES, DIVIDE, KEYWORD, INVALID,
        ASSIGNMENT_OPERATOR, SEMICOLON, DOT, LEFT_BRACE, RIGHT_BRACE, LPAREN, RPAREN,
        PRINT, CONCAT, NEW, CHARACTER, MAIN, CLOSE_QUOTE, MODULO, BOOLEAN, TRUE, FALSE
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
