package jeanscompiler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1️⃣ FLEXIBLE PATHING:
        // If an argument is provided, use it. Otherwise, look in the current folder.
        String filePath = args.length > 0 ? args[0] : "program.jeans";

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Error: Could not find file at " + file.getAbsolutePath());
            System.exit(1);
        }

        String source = new String(Files.readAllBytes(Paths.get(filePath)));

        // 2️⃣ Lex & Parse
        Lexer lexer = new Lexer(source);
        Parser parser = new Parser(lexer);
        AST.Program program = parser.parse();

        // 3️⃣ Generate Source (Just gets the string now)
        String javaCode = JavaGenerator.generate(program);

        // 4️⃣ (OPTIONAL) Run Locally
        // Since we removed running from JavaGenerator, we do it here for testing.
        System.out.println("--- Generated Java Code ---");
        System.out.println(javaCode);

        runLocally(javaCode);
    }

    // Helper to run code locally (simulating what the web portal does)
    private static void runLocally(String javaCode) throws Exception {
        // Write to a temporary file
        File tempFile = new File("Jeans.java");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(javaCode);
        }

        // Compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, tempFile.getAbsolutePath());

        if (result == 0) {
            System.out.println("\n--- Output ---");
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "jeanscompiler.Jeans");
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();
        } else {
            System.out.println("Compilation Failed.");
        }

        // Cleanup
        tempFile.delete();
        new File("jeanscompiler/Jeans.class").delete();
    }
}