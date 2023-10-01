package Semantic;

import Lexical.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteMethod {

    Token name;
    Token isStatic;
    Token type;
    HashMap<String, ConcreteAttribute> parameters;
    SymbolTable symbolTable;
    public ArrayList<ConcreteAttribute> parametersInOrder;

    public ConcreteMethod(Token name, Token type, Token isStatic, SymbolTable symbolTable) {
        this.name = name;
        this.type = type;
        this.isStatic = isStatic;
        this.symbolTable = symbolTable;
        parameters = new HashMap<>();
        parametersInOrder = new ArrayList<>();
    }

    public void addParameter(ConcreteAttribute p) throws SemanticException {
        if (!parameters.containsKey(p.name.getLexeme())){
            if (p.type.getLexeme().equals("void")){
                throw new SemanticException(p.name,"Parameter " + p.name.getLexeme() + " cannot be void in line "+ p.name.getRow());
            }else {
                parametersInOrder.add(p);
                parameters.put(p.name.getLexeme(), p);
            }
        }
        else
            throw new SemanticException(p.name,"Parameter " + p.name.getLexeme() + " already defined in line "+ p.name.getRow());
    }

    public void check() throws SemanticException {
        for (ConcreteAttribute p : parameters.values()){
            if (p.type.getName().equals("idClass")) {
                if (!symbolTable.classes.containsKey(p.type.getLexeme()) && !symbolTable.interfaces.containsKey(p.type.getLexeme()))
                    throw new SemanticException(p.type,"Class or interface " + p.type.getLexeme() + " not defined in line "+ p.type.getRow());
            } else if (p.type.getLexeme().equals("void")){
                throw new SemanticException(p.name,"Parameter " + p.name.getLexeme() + " cannot be void in line "+ p.name.getRow());
            }
        }
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
