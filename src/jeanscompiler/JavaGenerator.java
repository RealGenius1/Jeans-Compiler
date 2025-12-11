package jeanscompiler;

import jeanscompiler.AST.*;

public class JavaGenerator {

    public static String generate(Program program) {
        StringBuilder out = new StringBuilder();

        out.append("public class LolProgram {\n");
        out.append("  public static void main(String[] args) {\n");

        for (AST.Node n : program.statements) {

            if (n instanceof JortDecl vd) {
                out.append("    int ").append(vd.name).append(" = " + vd.val + ";\n");
            }

            else if (n instanceof Assignment as) {
                out.append("    ").append(as.name).append(" = ").append(as.value).append(";\n");
            }

            else if (n instanceof Print p) {
                out.append("    System.out.println(").append(p.value).append(");\n");
            }
            else if (n instanceof strDecl sd){
                out.append("    String ").append(sd.name).append(" = \"" + sd.val + "\";\n");
            }
        }

        out.append("  }\n}\n");
        return out.toString();
    }
}
