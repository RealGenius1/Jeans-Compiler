package jeanscompiler;

import java.util.*;
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

        eat(Type.HAI);

        while (look.type != Type.KTHXBYE && look.type != Type.EOF) {
            program.statements.add(statement());
        }

        eat(Type.KTHXBYE);

        return program;
    }

    private Node statement() {
        if (look.type == Type.I) {
            eat(Type.I); eat(Type.HAS); eat(Type.A);
            String name = look.text;
            eat(Type.IDENT);
            return new VarDecl(name);
        }

        if (look.type == Type.IDENT) {
            String name = look.text;
            eat(Type.IDENT);
            eat(Type.R);
            String value = look.text;
            eat(look.type == Type.NUMBER ? Type.NUMBER : Type.IDENT);
            return new Assignment(name, value);
        }

        if (look.type == Type.VISIBLE) {
            eat(Type.VISIBLE);
            String value = look.text;
            eat(look.type == Type.IDENT || look.type == Type.NUMBER ? look.type : null);
            return new Print(value);
        }

        throw new RuntimeException("Unknown statement start: " + look);
    }
}
