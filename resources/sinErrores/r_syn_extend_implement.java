///[SinErrores]
class Main {
    static void main(){}
}
class Class extends SuperClass{}
class SuperClass implements Interface{
    int m1(){
        return 0;
    }
    void m2(int a, int b){}
}

interface Interface{
    int m1();
    void m2(int a, int b);
}