package Semantic;

import Lexical.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteClass{
    Token name;
    Token implementsName;
    Token extendsName;
    HashMap<String, ConcreteMethod> methods;
    HashMap<String, ConcreteAttribute> attributes;
    public ConcreteMethod currentMethod;
    SymbolTable symbolTable;
    public ConcreteMethod constructor;
    private boolean consolidated = false;

    public ConcreteClass(Token token, SymbolTable symbolTable) {
        this.name = token;
        this.implementsName = new Token("", "-", -1);
        this.extendsName = new Token("", "Object", -1);
        methods = new HashMap<String, ConcreteMethod>();
        attributes = new HashMap<String, ConcreteAttribute>();
        this.symbolTable = symbolTable;
    }

    public void addConstructor(ConcreteMethod m) throws SemanticException {
        if (m.name.getLexeme().equals(name.getLexeme())){
            if (constructor == null){
                constructor = m;
            } else
                throw new SemanticException(m.name,"Constructor already defined in line "+ m.name.getRow());
        } else
            throw new SemanticException(m.name,"Constructor name must be the same as the class name in line "+ m.name.getRow());
    }

    public void addMethod(ConcreteMethod m) throws SemanticException {
        if (!methods.containsKey(m.name.getLexeme())){
            methods.put(m.name.getLexeme(), m);
        } else
            throw new SemanticException(m.name,"Method " + m.name.getLexeme() + " already defined in line "+ m.name.getRow());
    }

    public void addAttribute(ConcreteAttribute a) throws SemanticException {
        if (!attributes.containsKey(a.name.getLexeme()))
            if (a.type.getLexeme().equals("void")){
                throw new SemanticException(a.name,"Attribute " + a.name.getLexeme() + " cannot be void in line "+ a.name.getRow());
            }else {
                attributes.put(a.name.getLexeme(), a);
            }
        else
            throw new SemanticException(a.name,"Attribute " + a.name.getLexeme() + " already defined in line "+ a.name.getRow());
    }

    public void check() throws SemanticException {

        if (extendsName.getLexeme() != "$" && !symbolTable.classes.containsKey(extendsName.getLexeme()) && !symbolTable.interfaces.containsKey(extendsName.getLexeme()))
            throw new SemanticException(extendsName,"Class extended " + extendsName.getLexeme() + " not defined in line "+ extendsName.getRow());

        if (implementsName.getLexeme() != "-" && !symbolTable.interfaces.containsKey(implementsName.getLexeme()))
            throw new SemanticException(implementsName,"Interface implemented " + implementsName.getLexeme() + " not defined in line "+ implementsName.getRow());

        for (ConcreteMethod m : methods.values()){

            if (m.type.getName().equals("idClass")) {
                if (!symbolTable.classes.containsKey(m.type.getLexeme()) && !symbolTable.interfaces.containsKey(m.type.getLexeme()))
                    throw new SemanticException(m.type,"Class or interface " + m.type.getLexeme() + " not defined in line "+ m.type.getRow());
            }
            m.check();
        }

        for (ConcreteAttribute a : attributes.values()){
            if (a.type.getName().equals("idClass")) {
                if (!symbolTable.classes.containsKey(a.type.getLexeme()) && !symbolTable.interfaces.containsKey(a.type.getLexeme()))
                    throw new SemanticException(a.type,"Class or interface " + a.type.getLexeme() + " not defined in line "+ a.type.getRow());
            }
        }
    }

    public void consolidate(ArrayList parentsList) throws SemanticException {
        //if im in the list, there is a cycle then throw exception
        if (parentsList.contains(name.getLexeme()))
            throw new SemanticException(name,"Cycle detected in class " + name.getLexeme() + " in line "+ name.getRow());
        if (!consolidated){
            if (extendsName.getLexeme().equals("$")){}
            else if (extendsName.getLexeme().equals("Object")) inherit(extendsName.getLexeme());
            else {
                ConcreteClass parent = symbolTable.classes.get(extendsName.getLexeme());
                if (parent == null)
                    parent = symbolTable.interfaces.get(extendsName.getLexeme());
                parentsList.add(name.getLexeme());
                parent.consolidate(parentsList);
                inherit(extendsName.getLexeme());
            }
            consolidated = true;
        }
        if (constructor == null){
            constructor = new ConcreteMethod(name, name, new Token("", "-", -1), symbolTable);
        }
        checkCorrectInheritance();
    }

    private void checkCorrectInheritance() throws SemanticException {
        //check if all methods from the interfaces are implemented
        if (!implementsName.getLexeme().equals("-")){
            ConcreteClass parent = symbolTable.interfaces.get(implementsName.getLexeme());
            for (ConcreteMethod m : parent.methods.values()){
                if (!methods.containsKey(m.name.getLexeme()))
                    throw new SemanticException(m.name,"Method " + m.name.getLexeme() + " from interface " + implementsName.getLexeme() + " not implemented in line "+ m.name.getRow());
                else{
                    //check if method is overriden, if the signature is the same, same type of return and same type and order of parameters then its ok
                    ConcreteMethod current = methods.get(m.name.getLexeme());
                    if (!current.type.getLexeme().equals(m.type.getLexeme()))
                        throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " from interface " + implementsName.getLexeme() + " not implemented in line "+ m.name.getRow());
                    if (current.parameters.size() != m.parameters.size())
                        throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " from interface " + implementsName.getLexeme() + " not implemented in line "+ m.name.getRow());
                    //check if parameters are the same with the same order using parametersInOrder
                    for (int i = 0; i < current.parametersInOrder.size(); i++){
                        if (!current.parametersInOrder.get(i).type.getLexeme().equals(m.parametersInOrder.get(i).type.getLexeme()))
                            throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " from interface " + implementsName.getLexeme() + " not implemented in line "+ m.name.getRow());
                    }
                }
            }
        }
    }

    private void inherit(String lexeme) throws SemanticException {
        ConcreteClass parent = symbolTable.classes.get(lexeme);
        if (parent == null)
            parent = symbolTable.interfaces.get(lexeme);
        for (ConcreteAttribute a : parent.attributes.values()){
            if (!attributes.containsKey(a.name.getLexeme()))
                attributes.put(a.name.getLexeme(), a);
            else{
                //attributes cannot be overriden
                Token current = attributes.get(a.name.getLexeme()).name;
                throw new SemanticException(current,"Attribute " + a.name.getLexeme() + " already defined in the parent class "+ parent.name.getLexeme() + " in line "+ a.name.getRow());
            }
        }
        for (ConcreteMethod m : parent.methods.values()){
            if (!methods.containsKey(m.name.getLexeme()))
                methods.put(m.name.getLexeme(), m);
            else{
                //check if method is overriden, if the signature is the same, same type of return and same type and order of parameters then its ok
                ConcreteMethod current = methods.get(m.name.getLexeme());
                if (!current.type.getLexeme().equals(m.type.getLexeme()))
                    throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " already defined in the parent class "+ parent.name.getLexeme() + " in line "+ m.name.getRow());
                if (current.parameters.size() != m.parameters.size())
                    throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " already defined in the parent class "+ parent.name.getLexeme() + " in line "+ m.name.getRow());
                //check if parameters are the same with the same order using parametersInOrder
                for (int i = 0; i < current.parametersInOrder.size(); i++){
                    if (!current.parametersInOrder.get(i).type.getLexeme().equals(m.parametersInOrder.get(i).type.getLexeme()))
                        throw new SemanticException(current.name,"Method " + m.name.getLexeme() + " already defined in the parent class "+ parent.name.getLexeme() + " in line "+ m.name.getRow());
                }
            }
        }
    }

    public void setImplementsName(Token implementsName) {
        this.implementsName = implementsName;
    }

    public void setExtendsName(Token extendsName) {
        this.extendsName = extendsName;
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

}