package Semantic;

import Lexical.Token;

import java.util.HashMap;

public class ConcreteMethod {

    Token name;
    Token isStatic;
    Token type;
    HashMap<String, ConcreteAttribute> parameters;

    public ConcreteMethod() {
        name = new Token("", "", -1);
        type = new Token("", "", -1);
        isStatic = new Token("", "", -1);
        parameters = new HashMap<>();
    }

    public void addParameter(ConcreteAttribute p) {
        if (!parameters.containsKey(p.name.getLexeme()))
            parameters.put(p.name.getLexeme(), p);
        else
            System.out.println("Parameter " + p.name.getLexeme() + " already defined"); //TODO: throw exception
    }

    public void setName(Token name) {
        this.name = name;
    }

    public void setType(Token type) {
        this.type = type;
    }

    public void setIsStatic(Token isStatic) {
        this.isStatic = isStatic;
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public Token isStatic() {
        return isStatic;
    }

    public boolean isWellDeclared() {
        return !name.getLexeme().equals("") && !type.getLexeme().equals("");
    }
}
