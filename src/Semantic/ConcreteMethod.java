package Semantic;

import java.util.HashMap;

public class ConcreteMethod {
    boolean isStatic;
    String name;
    Type type;
    HashMap<String, ConcreteAttribute> parameters;

    public ConcreteMethod(String name, Type type, boolean isStatic) {
        this.name = name;
        this.type = type;
        this.isStatic = isStatic;
        parameters = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isWellDeclared() {
        return !name.equals("") && !type.equals("");
    }
}
