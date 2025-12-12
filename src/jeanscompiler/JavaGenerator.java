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
        out.append("\tpublic static void main(String[] args) {\n");
        out.append("\t\tScanner scan = new Scanner(System.in);\n");

        build(program, out, "\t\t");

        out.append("\t}\n}\n");
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

    public static void build(Program program, StringBuilder out, String indent) {
        for (AST.Node n : program.statements) {
            if (n instanceof JortDuckl vd) {
                out.append(indent + "int ").append(vd.name).append(" = " + vd.val + ";\n");
            } else if (n instanceof Assignment as) {
                out.append(indent).append(as.name).append(" = ").append(as.value).append(";\n");
            } else if (n instanceof Print p) {
                if(!p.isStr) {
                    out.append(indent + "System.out.println(").append(p.value).append(");\n");
                } else {
                    out.append(indent + "System.out.println(\"").append(p.value).append("\");\n");
                }
            } else if (n instanceof strDuckl sd) {
                out.append(indent + "String ").append(sd.name).append(" = \"" + sd.val + "\";\n");
            } else if (n instanceof vestDuckl vd) {
                out.append(indent + "char ").append(vd.name).append(" = " + vd.val + ";\n");
            } else if (n instanceof JeggingDuckl jd) {
                out.append(indent + "double ").append(jd.name).append(" = " + jd.val + ";\n");
            } else if (n instanceof hatDuckl vd) {
                out.append(indent + "boolean ").append(vd.name).append(" = " + vd.val + ";\n");
            } else if (n instanceof zipUp zu) {
                out.append(indent + zu.name + "++;\n");
            } else if (n instanceof zipDown zd) {
                out.append(indent + zd.name + "--;\n");
            } else if (n instanceof washDuckl wd) {
                out.append(indent + "System.gc();\n");
            } else if (n instanceof measureDuckl md) {
                out.append(indent + md.type + " " + md.name + " = " + "scan.next" + md.form + "();\n");
            } else if (n instanceof whileDuckl wd) {
                out.append(indent + "while (" + wd.ex + ") {\n");
                build(wd.block, out, indent+"\t");
                out.append(indent + "}\n");
            } else if (n instanceof ifDuckl id){
                out.append(indent + "if (" + id.ex + ") {\n");
                build(id.block, out, indent+"\t");
                out.append(indent + "}\n");
            } else if (n instanceof elseDuckl ed){
                out.append(indent + "else {\n");
                build(ed.block, out, indent+"\t");
                out.append(indent + "}\n");
            }
        }

    }
}