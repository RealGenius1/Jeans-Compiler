package jeanscompiler;

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
        for(String[] kw : keywords) {
            if(matchWord(kw[0])){
                return new Token(Token.Type.valueOf(kw[1]), kw[0]);
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

        throw new RuntimeException("Unknown token at " + pos);
    }

}
