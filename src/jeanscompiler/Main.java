package jeanscompiler;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String source = new String(java.nio.file.Files.readAllBytes(
                java.nio.file.Paths.get("program.lol")));

        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        AST.Program ast = parser.parse();

        String javaSrc = JavaGenerator.generate(ast);
        System.out.println("Generated Java:\n" + javaSrc);

        // Write Java file
        File javaFile = new File("LolProgram.java");
        try (FileWriter w = new FileWriter(javaFile)) {
            w.write(javaSrc);
        }

        // Compile it
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "LolProgram.java");
        if (result != 0) {
            System.err.println("Compilation failed");
            return;
        }

        // Run
        Class<?> cls = Class.forName("LolProgram");
        Method m = cls.getDeclaredMethod("main", String[].class);
        m.invoke(null, (Object) new String[]{});
    }
}
