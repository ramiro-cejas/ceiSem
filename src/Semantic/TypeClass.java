package Semantic;

public class TypeClass implements Type{
    public String name;
    public TypeClass(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypeClass)
            return ((TypeClass) obj).name.equals(this.name);
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Type other) {
        return false;
    }

    @Override
    public boolean isSubtype(Type other) {
        return false;
    }
}
