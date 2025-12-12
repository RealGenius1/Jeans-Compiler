package jeanscompiler;

import jeanscompiler.Token.Type;
import jeanscompiler.AST.*;

public class Parser {
    private final Lexer lexer;
    private Token look;
    private SymbolTable st = new SymbolTable();
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.look = lexer.next();
    }

    private void eat(Type t) {
        if (look.type != t) {
            System.out.println(look.text);
            throw new RuntimeException("Expected " + t + " got " + look.type);
        }
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
                    case BUTTON_UP:
                        eat(Type.BUTTON_UP);
                        if(look.type == Type.INTEGER) {
                            val += Integer.parseInt(look.text);
                            eat(Type.INTEGER);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val += (int) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case BUTTON_DOWN:
                        eat(Type.BUTTON_DOWN);
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
                    case DISCOUNT:
                        eat(Type.DISCOUNT);
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
            return new JortDuckl(name, val);
        }
        if (look.type == Type.JACKET) {
            eat(Type.JACKET);                  // consume 'jacket' keyword
            String name = look.text;           // capture identifier name
            eat(Type.IDENTIFIER);              // consume identifier
            eat(Type.ASSIGNMENT_OPERATOR);     // consume '='
            String value = look.text;
            eat(Type.STRING);
            eat(Type.SEMICOLON);               // consume string literal
            return new strDuckl(name, value);
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
                    case BUTTON_UP:
                        eat(Type.BUTTON_UP);
                        if(look.type == Type.DOUBLE) {
                            val += Double.parseDouble(look.text);
                            eat(Type.DOUBLE);
                        }
                        if(look.type == Type.IDENTIFIER) {
                            val += (double) st.getVal(look.text);
                            eat(Type.IDENTIFIER);
                        }
                        break;
                    case BUTTON_DOWN:
                        eat(Type.BUTTON_DOWN);
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
                    case DISCOUNT:
                        eat(Type.DISCOUNT);
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
            return new JeggingDuckl(name, val);
        }

        if(look.type == Type.VEST){
            eat(Type.VEST);
            String name = look.text;
            eat(Type.IDENTIFIER);
            eat(Type.ASSIGNMENT_OPERATOR);
            char c = look.text.charAt(0);
            eat(Type.CHARACTER);
            eat(Type.SEMICOLON);
            return new vestDuckl(name, c);
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
            System.out.println(value);
            boolean isStr = false;
            if(look.type == Type.IDENTIFIER || look.type == Type.INTEGER  || look.type == Type.DOUBLE)
                eat(look.type);
            else if (look.type == Type.STRING){
                eat(look.type);
                isStr = true;
            } else {
                eat(null);
            }
            eat(Type.RPAREN);
            eat(Type.SEMICOLON);
            return new Print(value, isStr);
        }

        if(look.type == Type.SEW){
            eat(Type.SEW);
            return new Print("", false);
        }
//        if(look.type == Type.SEMICOLON){
//            eat(Type.SEMICOLON);
//            return new Print("");
//        }
        if(look.type == Type.ZIP_UP){
            eat(Type.ZIP_UP);
            String name = look.text;
            eat(Type.IDENTIFIER);
            String type = st.get(name).getType();
            if(type == "Integer"){
                int val = (int) st.getVal(name);
                val++;
                st.upVal(name, val);
            } else if(type == "Double") {
                double val = (double) st.getVal(name);
                val++;
                st.upVal(name, val);
            } else {
                throw new ArithmeticException("Cannot zip up a jacket");
            }
            eat(Type.SEMICOLON);
            return new zipUp(name);

        }

        if(look.type == Type.ZIP_DOWN){
            eat(Type.ZIP_DOWN);
            String name = look.text;
            eat(Type.IDENTIFIER);
            String type = st.get(name).getType();
            if(type == "Integer"){
                int val = (int) st.getVal(name);
                val--;
                st.upVal(name, val);
            } else if(type == "Double") {
                double val = (double) st.getVal(name);
                val--;
                st.upVal(name, val);
            } else {
                throw new ArithmeticException("Cannot zip down a jacket");
            }
            eat(Type.SEMICOLON);
            return new zipDown(name);
        }
        else if (look.type == Type.WASH){
            eat(Type.WASH);
            eat(Type.SEMICOLON);
            return new washDuckl();
        }
        else if(look.type == Type.MEASURE){
            eat(Type.MEASURE);
            String form = "";
            String name = "";
            switch(look.type){
                case JORT:
                    form = "Int";
                    eat(Type.JORT);
                    name = look.text;
                    eat(Type.IDENTIFIER);
                    eat(Type.SEMICOLON);
                    return new measureDuckl(form, name, "int");
                case JEGGING:
                    form = "Double";
                    eat(Type.JEGGING);
                    name = look.text;
                    eat(Type.IDENTIFIER);
                    eat(Type.SEMICOLON);
                    return new measureDuckl(form, name, "double");
                case JACKET:
                    form = "Line";
                    eat(Type.JACKET);
                    name = look.text;
                    eat(Type.IDENTIFIER);
                    eat(Type.SEMICOLON);
                    return new measureDuckl(form, name, "String");
            }
        }
        if(look.type == Type.UNTIL){
            eat(Type.UNTIL);
            eat(Type.LPAREN);
            StringBuilder ex = new StringBuilder();
            while(look.type != Type.RPAREN){
                ex.append(look.text);
                try {
                    eat(look.type);
                } catch(RuntimeException E){
                    System.out.println("Catch");
                    look = lexer.next();
                }
            }
            eat(Type.RPAREN);
            eat(Type.LEFT_BRACE);
            Program block = new Program();
            while(look.type != Type.RIGHT_BRACE){
                block.statements.add(statement());
            }
            eat(Type.RIGHT_BRACE);
            return new whileDuckl(ex.toString(), block);
        }


        throw new RuntimeException("Unknown statement start: " + look);
    }
}
