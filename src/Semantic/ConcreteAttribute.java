package Semantic;

public class ConcreteAttribute {

    boolean isStatic;
    String name;
    String type;

    public ConcreteAttribute(String name, String type, boolean isStatic) {
        this.name = name;
        this.type = type;
        this.isStatic = isStatic;
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
