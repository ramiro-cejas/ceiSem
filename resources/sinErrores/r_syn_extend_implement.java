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
    static float m3(){
        return 0.0;
    }
}

interface Interface{
    int m1();
    float m3();
    void m2(int a, int b);
}