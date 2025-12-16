package jeanscompiler;

import jeanscompiler.AST.*;
import java.io.IOException;

public class JavaGenerator {

    /**
     * Generates Java source code string from the given Program AST.
     * Does NOT write to disk, compile, or run.
     */
    public static String generate(Program program) {
        StringBuilder out = new StringBuilder();

        // Standard boilerplate
        out.append("package jeanscompiler;\n\n");
        out.append("import java.util.Scanner;\n\n");
        out.append("public class Jeans {\n");
        out.append("\tpublic static void main(String[] args) {\n");
        out.append("\t\tScanner scan = new Scanner(System.in);\n");

        // Build the body
        build(program, out, "\t\t");

        // Close the class
        out.append("\t}\n}\n");

        // 🛑 DELETED: FileWriter, JavaCompiler, ProcessBuilder
        // The Web Portal (JeansService) handles execution.
        // The CLI (Main) handles execution.

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
            } else if (n instanceof elifDuckl ed){
                out.append(indent + "else if (" + ed.ex + ") {\n");
                build(ed.block, out, indent+"\t");
                out.append(indent + "}\n");
            } else if (n instanceof pocketDuckl pd){
                out.append(indent + pd.type + "[] " + pd.name + " = " + pd.ex + ";\n");
            } else if (n instanceof shoveDuckl sd){
                out.append(indent + sd.name + "[" + sd.ind + "] = " + sd.val + ";\n");
            } else if (n instanceof rummageDuckl rd){
                out.append(indent + rd.name + "[" + rd.ind + "]");
            }
        }

    }
}