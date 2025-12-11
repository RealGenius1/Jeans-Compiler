package jeanscompiler;
import java.util.*;
public class SymbolTable {
    private static Map<String, VariableSymbol> sTable = new HashMap<>();
    public static void add(String name, VariableSymbol sym)
    {
        sTable.put(name,sym);
    }

    public static void printVal(String n)
    {
        System.out.println("Var Name: " + n + " Var Type: " + sTable.get(n).getType() + " Var Value: " + sTable.get(n).getVal());
    }

    public static void upVal(String n, Object val)
    {
        sTable.get(n).setVal(val);
    }

    public static VariableSymbol get(String name)
    {
        return sTable.get(name);
    }
    public static Object getVal(String name){
        return sTable.get(name).getVal();
    }


    public static class VariableSymbol
    {
        String type;
        Object val;

        public VariableSymbol(String type, Object val)
        {
            this.type = type;

            if(val != null && val.getClass().getSimpleName().equalsIgnoreCase(type))
            {
                this.val = val;
            }
            else {
                throw new IllegalArgumentException("Value type does not match variable type");
            }
        }

        public void setVal(Object val)
        {
            this.val = val;
        }

        public String getType()
        {
            return type;
        }

        public Object getVal()
        {
            return val;
        }

    }
}
