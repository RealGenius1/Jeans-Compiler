package jeanscompiler;

import jeanscompiler.AST.*;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.IOException;

public class JavaGenerator {

    public static String generate(Program program) throws IOException, InterruptedException {
        StringBuilder out = new StringBuilder();

        out.append("package jeanscompiler;\n\n");
        out.append("import java.util.Scanner;\n\n");
        out.append("public class Jeans {\n");
        out.append("  public static void main(String[] args) {\n");
        out.append("    Scanner scan = new Scanner(System.in);\n");

        build(program, out);

        out.append("  }\n}\n");
//        File file = new File("Jeans.java");
        FileWriter writer = new FileWriter("C:\\Users\\sptho\\Downloads\\Github\\Jeans-Compiler\\src\\jeanscompiler\\Jeans.java");
        writer.write(out.toString());
        writer.close();

        String srcPath = "src/jeanscompiler/Jeans.java";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, srcPath);

        if (result != 0) {
            System.out.println("Compilation failed");
        }

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp", "src",
                "jeanscompiler.Jeans"
        );

        pb.inheritIO();
        Process p = pb.start();
        p.waitFor();

        return out.toString();
    }

    public static void build(Program program, StringBuilder out) {
        for (AST.Node n : program.statements) {
            if (n instanceof JortDuckl vd) {
                out.append("    int ").append(vd.name).append(" = " + vd.val + ";\n");
            } else if (n instanceof Assignment as) {
                out.append("    ").append(as.name).append(" = ").append(as.value).append(";\n");
            } else if (n instanceof Print p) {
                if(!p.isStr) {
                    out.append("    System.out.println(").append(p.value).append(");\n");
                } else {
                    out.append("    System.out.println(\"").append(p.value).append("\");\n");
                }
            } else if (n instanceof strDuckl sd) {
                out.append("    String ").append(sd.name).append(" = \"" + sd.val + "\";\n");
            } else if (n instanceof vestDuckl vd) {
                out.append("    char ").append(vd.name).append(" = '" + vd.val + "';\n");
            } else if (n instanceof JeggingDuckl jd) {
                out.append("    double ").append(jd.name).append(" = " + jd.val + ";\n");
            } else if (n instanceof hatDuckl vd) {
                out.append("    boolean ").append(vd.name).append(" = " + vd.val + ";\n");
            } else if (n instanceof zipUp zu) {
                out.append("     " + zu.name + "++;\n");
            } else if (n instanceof zipDown zd) {
                out.append("     " + zd.name + "--;\n");
            } else if (n instanceof washDuckl wd) {
                out.append("    System.gc();\n");
            } else if (n instanceof measureDuckl md) {
                out.append("    " + md.type + " " + md.name + " = " + "scan.next" + md.form + "();\n");
            } else if (n instanceof whileDuckl wd) {
                out.append("    while (" + wd.ex + ") {\n");
                build(wd.block, out);
                out.append("    }\n");
            }
        }

    }
}