package jeanscompiler;

import java.util.*;


// TODO: RENAME ALL DECLs to DUCKLs

public class AST {
    public interface Node {}

    public static class Program implements Node {
        public final List<Node> statements = new ArrayList<>();
    }

    public static class JortDecl implements Node {
        public final String name;
        public final int val;
        public JortDecl(String name, int val) {
            this.name = name;
            this.val = val;
        }
    }
    public static class JeggingDecl implements Node {
        public final String name;
        public final double val;
        public JeggingDecl(String name, double val){
            this.name = name;
            this.val = val;
        }
    }

    public static class vestDecl implements Node{
        public final String name;
        public final char val;
        public vestDecl(String name, char val){
            this.name = name;
            this.val = val;
        }
    }

    public static class hatDuckl implements Node{
        public final String name;
        public final boolean val;
        public hatDuckl(String name, boolean val){
            this.name = name;
            this.val = val;
        }
    }

    public static class strDecl implements Node{
        public final String name;
        public final String val;
        public strDecl(String name, String val){
            this.name = name;
            this.val = val;
        }
    }

    public static class Assignment implements Node {
        public final String name;
        public final String value;
        public Assignment(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    public static class Print implements Node {
        public final String value;
        public Print(String v) { this.value = v; }
    }
}
