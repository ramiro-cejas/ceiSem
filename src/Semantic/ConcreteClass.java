package Semantic;

import java.util.HashMap;

public class ConcreteClass {
    String name;
    String implementsName;
    String extendsName;
    HashMap<String, ConcreteMethod> methods;
    HashMap<String, ConcreteAttribute> attributes;

    public ConcreteClass(String name) {
        this.name = name;
        this.implementsName = "";
        this.extendsName = "";
        methods = new HashMap<String, ConcreteMethod>();
        attributes = new HashMap<String, ConcreteAttribute>();
    }

    public void addMethod(ConcreteMethod m) {
        if (!methods.containsKey(m.name))
            methods.put(m.name, m);
        else
            System.out.println("Method " + m.name + " already defined");
    }

    public void addAttribute(ConcreteAttribute a) {
        if (!attributes.containsKey(a.name))
            attributes.put(a.name, a);
        else
            System.out.println("Attribute " + a.name + " already defined");
    }

    public void setImplementsName(String name) {
        this.implementsName = name;
    }

    public void setExtendsName(String name) {
        this.extendsName = name;
    }

    public String getName() {
        return name;
    }

    public String getImplementsName() {
        return implementsName;
    }

    public String getExtendsName() {
        return extendsName;
    }

    public boolean isWellDeclared() {
        for (ConcreteMethod m : methods.values())
            if (!m.isWellDeclared())
                return false;
        for (ConcreteAttribute a : attributes.values())
            if (!a.isWellDeclared())
                return false;
        return !name.isEmpty();
    }

}
