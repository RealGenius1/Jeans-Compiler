package jeanscompiler;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import jeanscompiler.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String source = new String(Files.readAllBytes(Paths.get("C:\\Users\\sptho\\Downloads\\Github\\Jeans-Compiler\\src\\jeanscompiler\\program.jeans")));

        // 2️⃣ Lex the source
        Lexer lexer = new Lexer(source);

        // 3️⃣ Parse into AST
        Parser parser = new Parser(lexer);
        AST.Program program = parser.parse(); // assuming parseProgram() returns the root AST

        // 4️⃣ Print the AST (optional, useful for debugging)
//        System.out.println(program.prettyPrint());

        // 5️⃣ (Optional) interpret or execute the AST
         JavaGenerator interpreter = new JavaGenerator();
         interpreter.generate(program);

    }
}