package Semantic;

import Lexical.Token;

import java.util.HashMap;
import java.util.HashSet;

public class SymbolTable {
    public HashMap<String, ConcreteClass> classes;
    public ConcreteClass currentClass;

    /* # is the empty class Object */
    public SymbolTable() {
        classes = new HashMap<>();
        currentClass = null;
        addDefaultsClasses();
    }

    private void addDefaultsClasses() {
        ConcreteClass object = new ConcreteClass(new Token("Object", "Object", 0));
        object.setExtendsName(new Token("","",-1));
        object.setImplementsName(new Token("","",-1));
        addClass(object);

        ConcreteClass system = new ConcreteClass(new Token("System", "System", 0));
        system.setExtendsName(new Token("Object","Object",-1));
        system.setImplementsName(new Token("","",-1));
        addClass(system);

        ConcreteClass string = new ConcreteClass(new Token("String", "String", 0));
        string.setExtendsName(new Token("Object","Object",-1));
        string.setImplementsName(new Token("","",-1));
        addClass(string);
    }

    public void addClass(ConcreteClass c) {
        if (!classes.containsKey(c.name.getLexeme())){
            classes.put(c.name.getLexeme(), c);
        }
        else
            System.out.println("Class " + c.name + " already defined"); //TODO: throw exception
    }

    public boolean isWellDeclared(){
        boolean wellDeclared = true;
        for (ConcreteClass c : classes.values()){
            wellDeclared = wellDeclared && c.isWellDeclared();
        }
        return wellDeclared;
    }
}