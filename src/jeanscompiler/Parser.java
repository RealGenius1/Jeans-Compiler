package jeanscompiler;

import jeanscompiler.Token.Type;
import jeanscompiler.AST.*;
import java.util.Map;

public class Parser {
    private final Lexer lexer;
    private Token look;
    private SymbolTable st = new SymbolTable();
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.look = lexer.next();
    }

    private void eat(Type t) {
        if (look.type != t)
            throw new RuntimeException("Expected " + t + " got " + look.type);
        look = lexer.next();
    }

    public Program parse() {
        Program program = new Program();

        eat(Type.CATWALK);

        while (look.type != Type.EVERYTHING_MUST_GO && look.type != Type.EOF) {
            program.statements.add(statement());
        }

        eat(Type.EVERYTHING_MUST_GO);

        return program;
    }

    private Node statement() {
        if (look.type == Type.JORT) {
            eat(Type.JORT);
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            int val = 0;
            switch (look.type) {
                case INTEGER:
                    val += Integer.parseInt(look.text);
                    eat(Type.INTEGER);
                    break;
                case IDENTIFIER:
                    val += (int) st.getVal(look.text);
                    eat(Type.IDENTIFIER);
                    break;
            }
            while(look.type != Type.SEMICOLON) {
                switch (look.type) {
                    case PLUS:
                        eat(Type.PLUS);
                        if(look.type == Type.INTEGER) {
                            val += Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val += (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case MINUS:
                        eat(Type.MINUS);
                        if(look.type == Type.INTEGER) {
                            val -= Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val -= (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case TIMES:
                        eat(Type.TIMES);
                        if(look.type == Type.INTEGER) {
                            val *= Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val *= (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case DIVIDE:
                        eat(Type.DIVIDE);
                        if(look.type == Type.INTEGER) {
                            val /= Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val /= (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case MODULO:
                        eat(Type.MODULO);
                        if(look.type == Type.INTEGER) {
                            val %= Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val %= (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                }
            }

            //eat(Type.INTEGER);
            eat(Type.SEMICOLON);
            st.add(name, new SymbolTable.VariableSymbol("Integer", val));
            return new JortDecl(name, val);
        }
        if (look.type == Type.JACKET) {
            eat(Type.JACKET);                  // consume 'jacket' keyword
            String name = look.text;           // capture identifier name
            eat(Type.IDENTIFIER);              // consume identifier
            eat(Type.ASSIGNMENT_OPERATOR);     // consume '='
            String value = look.text;
            eat(Type.STRING);
            eat(Type.SEMICOLON);               // consume string literal
            return new strDecl(name, value);
        }

        if(look.type == Type.JEGGING){
            eat(Type.JEGGING);
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            double val = 0;
            switch (look.type) {
                case DOUBLE:
                    val += Double.parseDouble(look.text);
                    eat(Type.DOUBLE);
                    break;
                case IDENTIFIER:
                    val += (double) st.getVal(look.text);
                    eat(Type.IDENTIFIER);
                    break;
            }
            while(look.type != Type.SEMICOLON) {
                switch (look.type) {
                    case PLUS:
                        eat(Type.PLUS);
                        if(look.type == Type.DOUBLE) {
                            val += Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val += (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case MINUS:
                        eat(Type.MINUS);
                        if(look.type == Type.DOUBLE) {
                            val -= Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val -= (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case TIMES:
                        eat(Type.TIMES);
                        if(look.type == Type.DOUBLE) {
                            val *= Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val *= (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case DIVIDE:
                        eat(Type.DIVIDE);
                        if(look.type == Type.DOUBLE) {
                            val /= Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val /= (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case MODULO:
                        eat(Type.MODULO);
                        if(look.type == Type.DOUBLE) {
                            val %= Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val %= (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                }
            }
            eat(Type.SEMICOLON);
            st.add(name, new SymbolTable.VariableSymbol("Double", val));
            return new JeggingDecl(name, val);
        }

        if(look.type == Type.VEST){
            eat(Type.VEST);
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            char c = look.text.charAt(0);
            eat(Type.CHARACTER);
            eat(Type.SEMICOLON);
            return new vestDecl(name, c);
        }

        if(look.type == Type.HAT){
            eat(Type.HAT);
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            boolean c;
            if(look.type == Type.TRUE){
                c = true;
                eat(Type.TRUE);
            } else {
                c = false;
                eat(Type.FALSE);
            }
//            eat(Type.BOOLEAN);
            eat(Type.SEMICOLON);
            return new hatDuckl(name, c);
        }


        if (look.type == Type.IDENTIFIER) {
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            String value = look.text;
            eat(look.type == Type.INTEGER ? Type.INTEGER : Type.IDENTIFIER);
            eat(Type.SEMICOLON);
            return new Assignment(name, value);
        }

        if (look.type == Type.ADVERTISE) {
            eat(Type.ADVERTISE);
            eat(Type.LPAREN);
            String value = look.text;
            eat(look.type == Type.IDENTIFIER || look.type == Type.INTEGER ? look.type : null);
            eat(Type.RPAREN);
            eat(Type.SEMICOLON);
            return new Print(value);
        }

        if(look.type == Type.SEW){
            eat(Type.SEW);
            return new Print("");
        }
//        if(look.type == Type.SEMICOLON){
//            eat(Type.SEMICOLON);
//            return new Print("");
//        }

        throw new RuntimeException("Unknown statement start: " + look);
    }
}
