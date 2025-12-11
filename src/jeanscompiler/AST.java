package jeanscompiler;

import java.util.*;

public class AST {
    public interface Node {}

    public static class Program implements Node {
        public final List<Node> statements = new ArrayList<>();
    }

    public static class VarDecl implements Node {
        public final String name;
        public VarDecl(String name) { this.name = name; }
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
