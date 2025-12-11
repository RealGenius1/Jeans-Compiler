package jeanscompiler;

import jeanscompiler.Token.Type;
import jeanscompiler.AST.*;

public class Parser {
    private final Lexer lexer;
    private Token look;

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
            int value = Integer.parseInt(look.text);
            eat(Type.INTEGER);
            eat(Type.SEMICOLON);
            return new JortDecl(name, value);
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
            double val = Double.parseDouble(look.text);
            eat(Type.DOUBLE);
            eat(Type.SEMICOLON);
            return new JeggingDecl(name, val);
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
