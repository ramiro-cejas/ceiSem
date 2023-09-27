package Semantic;

import Lexical.Token;

import java.util.HashMap;

public class ConcreteClass {
    Token isStatic;
    Token name;
    Token implementsName;
    Token extendsName;
    HashMap<String, ConcreteMethod> methods;
    HashMap<String, ConcreteAttribute> attributes;
    ConcreteMethod currentMethod;

    public ConcreteClass(Token token) {
        this.name = token;
        this.implementsName = new Token("", "", -1);
        this.extendsName = new Token("", "", -1);
        methods = new HashMap<String, ConcreteMethod>();
        attributes = new HashMap<String, ConcreteAttribute>();
        this.isStatic = new Token("", "", -1);
    }

    public void setIsStatic(Token isStatic) {
        this.isStatic = isStatic;
    }

    public Token getStatic() {
        return isStatic;
    }

    public void addMethod(ConcreteMethod m) {
        if (!methods.containsKey(m.name.getLexeme()))
            methods.put(m.name.getLexeme(), m);
        else
            System.out.println("Method " + m.name.getLexeme() + " already defined"); //TODO: throw exception
    }

    public void addAttribute(ConcreteAttribute a) {
        if (!attributes.containsKey(a.name.getLexeme()))
            attributes.put(a.name.getLexeme(), a);
        else
            System.out.println("Attribute " + a.name.getLexeme() + " already defined"); //TODO: throw exception
    }

    public void setImplementsName(Token name) {
        this.implementsName = name;
    }

    public void setExtendsName(Token name) {
        this.extendsName = name;
    }

    public Token getName() {
        return name;
    }

    public Token getImplementsName() {
        return implementsName;
    }

    public Token getExtendsName() {
        return extendsName;
    }

    public boolean isWellDeclared() {
        for (ConcreteMethod m : methods.values())
            if (!m.isWellDeclared())
                return false;
        for (ConcreteAttribute a : attributes.values())
            if (!a.isWellDeclared())
                return false;
        return !name.getLexeme().isEmpty();
    }

}