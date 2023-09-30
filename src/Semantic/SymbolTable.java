package Semantic;

import Lexical.Token;

import java.util.HashMap;
import java.util.HashSet;

public class SymbolTable {
    public HashMap<String, ConcreteClass> classes;
    public HashMap<String, ConcreteClass> interfaces;
    public ConcreteClass currentClass;

    public SymbolTable() throws SemanticException {
        classes = new HashMap<>();
        interfaces = new HashMap<>();
        currentClass = null;
        addDefaultsClasses();
    }

    private void addDefaultsClasses() throws SemanticException {
        ConcreteClass object = new ConcreteClass(new Token("Object", "Object", -1), this);
        object.extendsName = (new Token("$","$",-1));
        addObjectMethods(object);
        addClass(object);

        ConcreteClass system = new ConcreteClass(new Token("System", "System", -1), this);
        addSystemMethods(system);
        addClass(system);

        ConcreteClass string = new ConcreteClass(new Token("String", "String", -1), this);
        addClass(string);
    }

    private void addSystemMethods(ConcreteClass system) throws SemanticException {
        ConcreteMethod method = new ConcreteMethod(new Token("idMetVar", "read", -1), new Token("keyword_int", "int", -1), new Token("keyword_static", "static", -1), this);
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printB", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","b",-1), new Token("keyword_boolean","boolean",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printC", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","c",-1), new Token("keyword_char","char",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printI", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","i",-1), new Token("keyword_int","int",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printS", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","s",-1), new Token("keyword_String","String",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "println", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printBln", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","b",-1), new Token("keyword_boolean","boolean",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printCln", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","c",-1), new Token("keyword_char","char",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printIln", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","i",-1), new Token("keyword_int","int",-1), new Token("-", "-", -1)));
        system.addMethod(method);

        method = new ConcreteMethod(new Token("idMetVar", "printSln", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","s",-1), new Token("keyword_String","String",-1), new Token("-", "-", -1)));
        system.addMethod(method);
    }

    private void addObjectMethods(ConcreteClass object) throws SemanticException {
        ConcreteMethod method = new ConcreteMethod(new Token("idMetVar", "debugPrint", -1), new Token("keyword_void", "void", -1), new Token("keyword_static", "static", -1), this);
        method.addParameter(new ConcreteAttribute(new Token("idMetVar","i",-1), new Token("keyword_int","int",-1), new Token("-", "-", -1)));
        object.addMethod(method);
    }

    public void addClass(ConcreteClass c) throws SemanticException {
        if (!classes.containsKey(c.name.getLexeme())){
            classes.put(c.name.getLexeme(), c);
        }
        else
            throw new SemanticException(c.name,"Class " + c.name.getLexeme() + " already defined in line "+ c.name.getRow());
    }

    public void addInterface(ConcreteClass c) throws SemanticException {
        if (!interfaces.containsKey(c.name.getLexeme())){
            interfaces.put(c.name.getLexeme(), c);
        }
        else
            throw new SemanticException(c.name,"Interface " + c.name.getLexeme() + " already defined in line "+ c.name.getRow());
    }

    public void check() throws SemanticException {
        for (ConcreteClass c : classes.values()){
            c.check();
            c.consolidate();
        }
    }

    public String toString(){
        StringBuilder toReturn = new StringBuilder();

        for (ConcreteClass c : interfaces.values()){
            toReturn.append("Interface: ").append(c.name.getLexeme()).append("\n");
            if (!c.extendsName.getLexeme().equals("-"))
                toReturn.append("├─ Extends: ").append(c.extendsName.getLexeme()).append("\n");
            if (c.attributes.isEmpty())
                toReturn.append("├─ No attributes\n");
            else
                toReturn.append("├─ Attributes:\n");

            for (ConcreteAttribute a : c.attributes.values()){
                if (c.attributes.values().toArray()[c.attributes.size()-1] == a)
                    toReturn.append("│   └─ ").append(a.name.getLexeme()).append(" : ").append(a.type.getLexeme()).append("\n");
                else
                    toReturn.append("│   ├─ ").append(a.name.getLexeme()).append(" : ").append(a.type.getLexeme()).append("\n");
            }

            if (c.methods.isEmpty())
                toReturn.append("└─ No methods\n");
            else{
                toReturn.append("└─ Methods:\n");

                for (ConcreteMethod m : c.methods.values()){
                    if (c.methods.values().toArray()[c.methods.size()-1] == m){
                        toReturn.append("    └─ ").append(m.name.getLexeme()).append(" : ").append(m.type.getLexeme()).append("\n");
                        if (!m.isStatic.getLexeme().equals("-"))
                            toReturn.append("        ├─ Static: ").append(m.isStatic.getLexeme()).append("\n");
                        if (m.parameters.isEmpty())
                            toReturn.append("        └─ No parameters\n");
                        else
                            toReturn.append("        └─ Parameters:\n");
                        for (ConcreteAttribute p : m.parameters.values()){
                            if (m.parameters.values().toArray()[m.parameters.size()-1] == p)
                                toReturn.append("            └─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                            else
                                toReturn.append("            ├─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                        }
                    }else{
                        toReturn.append("    ├─ ").append(m.name.getLexeme()).append(" : ").append(m.type.getLexeme()).append("\n");
                        if (!m.isStatic.getLexeme().equals("-"))
                            toReturn.append("    │   ├─ Static: ").append(m.isStatic.getLexeme()).append("\n");                        toReturn.append("    │   └─ Parameters:\n");
                        for (ConcreteAttribute p : m.parameters.values()){
                            if (m.parameters.values().toArray()[m.parameters.size()-1] == p)
                                toReturn.append("    │       └─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                            else
                                toReturn.append("    │       ├─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                        }
                    }
                }
            }
            toReturn.append("--------------------------------\n");
        }

        for (ConcreteClass c : classes.values()){
            toReturn.append("Class: ").append(c.name.getLexeme()).append("\n");
            if (!c.extendsName.getLexeme().equals("-"))
                toReturn.append("├─ Extends: ").append(c.extendsName.getLexeme()).append("\n");
            if (!c.implementsName.getLexeme().equals("-"))
                toReturn.append("├─ Implements: ").append(c.implementsName.getLexeme()).append("\n");
            if (c.attributes.isEmpty())
                toReturn.append("├─ No attributes\n");
            else
                toReturn.append("├─ Attributes:\n");

            for (ConcreteAttribute a : c.attributes.values()){
                if (c.attributes.values().toArray()[c.attributes.size()-1] == a)
                    toReturn.append("│   └─ ").append(a.name.getLexeme()).append(" : ").append(a.type.getLexeme()).append("\n");
                else
                    toReturn.append("│   ├─ ").append(a.name.getLexeme()).append(" : ").append(a.type.getLexeme()).append("\n");
            }

            if (c.methods.isEmpty())
                toReturn.append("└─ No methods\n");
            else{
                toReturn.append("└─ Methods:\n");

                for (ConcreteMethod m : c.methods.values()){
                    if (c.methods.values().toArray()[c.methods.size()-1] == m){
                        toReturn.append("    └─ ").append(m.name.getLexeme()).append(" : ").append(m.type.getLexeme()).append("\n");
                        if (!m.isStatic.getLexeme().equals("-"))
                            toReturn.append("        ├─ Static: ").append(m.isStatic.getLexeme()).append("\n");
                        if (m.parameters.isEmpty())
                            toReturn.append("        └─ No parameters\n");
                        else
                            toReturn.append("        └─ Parameters:\n");
                        for (ConcreteAttribute p : m.parameters.values()){
                            if (m.parameters.values().toArray()[m.parameters.size()-1] == p)
                                toReturn.append("            └─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                            else
                                toReturn.append("            ├─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                        }
                    }else{
                        toReturn.append("    ├─ ").append(m.name.getLexeme()).append(" : ").append(m.type.getLexeme()).append("\n");
                        if (!m.isStatic.getLexeme().equals("-"))
                            toReturn.append("    │   ├─ Static: ").append(m.isStatic.getLexeme()).append("\n");                        toReturn.append("    │   └─ Parameters:\n");
                        for (ConcreteAttribute p : m.parameters.values()){
                            if (m.parameters.values().toArray()[m.parameters.size()-1] == p)
                                toReturn.append("    │       └─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                            else
                                toReturn.append("    │       ├─ ").append(p.name.getLexeme()).append(" : ").append(p.type.getLexeme()).append("\n");
                        }
                    }
                }
            }
            toReturn.append("--------------------------------\n");
        }

        return toReturn.toString();
    }
}