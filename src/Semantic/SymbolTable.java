package Semantic;

import java.util.HashMap;

public class SymbolTable {

    HashMap<String, ConcreteClass> classes;
    ConcreteMethod currentMethod;
    ConcreteClass currentClass;

    /* # is the empty class Object */
    public SymbolTable() {
        classes = new HashMap<>();
        classes.put("#", new ConcreteClass("#"));
        currentMethod = null;
        currentClass = null;
    }

    public void addClass(ConcreteClass c) {
        if (!classes.containsKey(c.name))
            classes.put(c.name, c);
        else
            System.out.println("Class " + c.name + " already defined");
    }


}
