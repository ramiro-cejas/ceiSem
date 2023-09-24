package Semantic;

public interface Type {

    boolean equals(Type other);

    boolean isSubtype(Type other);
}