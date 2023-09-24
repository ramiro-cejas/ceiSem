package Semantic;

import java.util.HashMap;

public class ConcreteMethod {
    boolean isStatic;
    String name;
    String type;
    HashMap<String, ConcreteAttribute> parameters;

    public ConcreteMethod(String name, String type, boolean isStatic) {
        this.name = name;
        this.type = type;
        this.isStatic = isStatic;
        parameters = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isWellDeclared() {
        return !name.equals("") && !type.equals("");
    }
}
