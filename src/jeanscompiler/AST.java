package jeanscompiler;

import java.util.*;


// TODO: RENAME ALL DECLs to DUCKLs

public class AST {
    public interface Node {}

    public static class Program implements Node {
        public final List<Node> statements = new ArrayList<>();
    }

    public static class JortDuckl implements Node {
        public final String name;
        public final int val;
        public JortDuckl(String name, int val) {
            this.name = name;
            this.val = val;
        }
    }
    public static class JeggingDuckl implements Node {
        public final String name;
        public final double val;
        public JeggingDuckl(String name, double val){
            this.name = name;
            this.val = val;
        }
    }

    public static class vestDuckl implements Node{
        public final String name;
        public final String val;
        public vestDuckl(String name, String val){
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

    public static class strDuckl implements Node{
        public final String name;
        public final String val;
        public strDuckl(String name, String val){
            this.name = name;
            this.val = val;
        }
    }

    public static class zipUp implements Node{
        public final String name;
        public zipUp(String name){
            this.name = name;
        }
    }

    public static class zipDown implements Node{
        public final String name;
        public zipDown(String name){
            this.name = name;
        }
    }

    public static class washDuckl implements Node{

    }

    public static class measureDuckl implements Node{
        public final String name;
        public final String form;
        public final String type;
        public measureDuckl(String form, String name, String type){
            this.name = name;
            this.form = form;
            this.type = type;
        }
    }

    public static class whileDuckl implements Node{
        public final String ex;
        public final Program block;
        public whileDuckl(String ex, Program block){
            this.ex = ex;
            this.block = block;
        }
    }

    public static class ifDuckl implements Node{
        public final String ex;
        public final Program block;
        public ifDuckl(String ex, Program block){
            this.ex = ex;
            this.block = block;
        }
    }

    public static class elifDuckl implements Node{
        public final String ex;
        public final Program block;
        public elifDuckl(String ex, Program block){
            this.ex = ex;
            this.block = block;
        }
    }

    public static class elseDuckl implements Node {
        public final Program block;
        public elseDuckl(Program block){
            this.block = block;
        }
    }

    public static class pocketDuckl implements Node {
        public final String name;
        public final String type;
        public final String ex;
        public pocketDuckl(String name, String type, String ex){
            this.name = name;
            this.type = type;
            this.ex = ex;
        }
    }

    public static class shoveDuckl implements Node {
        public final String name;
        public final String val;
        public final int ind;
        public shoveDuckl(String name, String val, int ind){
            this.name = name;
            this.val = val;
            this.ind = ind;
        }
    }

    public static class rummageDuckl implements Node {
        public final String name;
        public final int ind;
        public rummageDuckl(String name, int ind){
            this.name = name;
            this.ind = ind;
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
        public final boolean isStr;
        public Print(String v, boolean isStr) {
            this.value = v;
            this.isStr = isStr;
        }
    }
}
