package Semantic;

import java.util.HashMap;
import java.util.HashSet;

public class SymbolTable {
    public HashMap<String, ConcreteClass> classes;
    public ConcreteMethod currentMethod;
    public ConcreteClass currentClass;

    /* # is the empty class Object */
    public SymbolTable() {
        classes = new HashMap<>();
        classes.put("#", new ConcreteClass("#"));
        currentMethod = null;
        currentClass = null;
    }

    public void addClass(ConcreteClass c) {
        if (!classes.containsKey(c.name)){
            classes.put(c.name, c);
        }
        else
            System.out.println("Class " + c.name + " already defined");
    }

    public boolean isWellDeclared(){
        boolean wellDeclared = true;
        for (ConcreteClass c : classes.values()){
            wellDeclared = wellDeclared && c.isWellDeclared();
        }
        return wellDeclared;
    }
}