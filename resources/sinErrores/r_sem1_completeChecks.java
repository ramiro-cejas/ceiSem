///[SinErrores]
class Main {
    static void main(){}
}
class Class {
    char method() {

        var c = 'c';

    }
}

class Class1 {
    char method() {

        a.b().c.d();

    }
}

class Class3 {
    // Operadores Lógicos
    boolean expresion1 = true;
    boolean expresion2 = false;
    boolean condicion1 = true;

    // Operador OR Lógico (||) y Operador AND Lógico (&&) combinados
    boolean resultadoCompuesto1 = (expresion1 || expresion2) && condicion1;

    // Operadores de Comparación
    int variable1 = 10;
    int variable2 = 20;
    int numero = -5;

    // Operador Igualdad (==) y Operador Menor que (<) combinados
    boolean resultadoCompuesto2 = (variable1 == variable2) || (variable1 < variable2);

    // Operador Desigualdad (!=) y Operador de Signo (+) combinados
    boolean resultadoCompuesto3 = (numero != 0) && (numero > 0);

}

class Class4 {
    public Class4(int a, char b, boolean c){}
}

class Class5 extends SuperClass{}
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

class Class6 {
    // Ejemplos de valores float
    float f1 = 3.14e0;         // Notación científica
    float f2 = 31.4E-1;        // Notación científica
    float f3 = 0.0314e2;       // Notación científica
    float f4 = 3.14;           // Notación normal

    // Operaciones con float
    float suma = f1 + f2;     // Suma de dos floats
    float resta = f1 - f2;    // Resta de dos floats
    float multiplicacion = f1 * f2;  // Multiplicación de dos floats
    float division = f1 / f2;        // División de dos floats
    float modulo = f1 % f2;          // Módulo de dos floats

    // Comparaciones con float
    boolean igualdad = f1 == f2;  // Comparación de igualdad
    boolean desigualdad = f1 != f2; // Comparación de desigualdad
    boolean mayorQue = f1 > f2;   // Comparación mayor que
    boolean menorQue = f1 < f2;   // Comparación menor que
    boolean mayorOIgual = f1 >= f2; // Comparación mayor o igual que
    boolean menorOIgual = f1 <= f2; // Comparación menor o igual que

    // Llamada a métodos que reciben y retornan float
    float resultado1 = calcularPromedio(f1, f2);

    float resultado2 = calcularAreaCirculo(2.5);

    // Método que recibe dos floats y retorna un float (calcular promedio)
    static float calcularPromedio(float num1, float num2) {
        return (num1 + num2) / 2.0;
    }

    // Método que recibe un float y retorna un float (calcular área de un círculo)
    float calcularAreaCirculo(float radio) {
        return 3.14 * radio * radio;
    }
}

class CombinacionesIf {
    static void main(String args) {
        var numero = 10;
        var esVerdadero = true;
        var esFalso = false;

        // Primer if
        if (numero > 5) {
            println("Número es mayor que 5");
        } else {
            // Segundo if dentro del else del primer if
            if (esVerdadero) {
                println("Es verdadero");
            }
        }

        // Otro ejemplo
        if (esFalso) {
            println("Es falso");
        } else {
            // Segundo if dentro del else del primer if
            if (numero >= 10) {
                println("Número es mayor o igual a 10");
            }
        }

        if (numero > 5) println("Número es mayor que 5");
        else if (numero > 5) println("Número es mayor que 5");

        if (numero < 0) {
            println("Número es negativo");
        } else if (numero > 0) {
            println("Número es positivo");
        } else {
            println("Número es cero");
        }
    }
}

interface Interface2 {
    void method();
    void method2();
    void method3();
}

class Class7 {
    static int y, z = 10; // y y z son atributos estáticos
    static int x = 5;  // x y y son atributos estáticos
    String nombre = "Juan";
    float salario, bono = 1000.50;
    int edad = 25;
    boolean esEstudiante = true;
    char letraA = 'A';
    int numero = 42;
    String mensaje = "Hola, mundo!";
    boolean esVerdadero = false;
}

class Class8 {
    // Operador OR Lógico (||)
    boolean expresion1 = true;
    boolean expresion2 = false;
    boolean resultadoOr = expresion1 || expresion2;

    // Operador AND Lógico (&&)
    boolean condicion1 = true;
    boolean condicion2 = true;
    boolean resultadoAnd = condicion1 && condicion2;

    // Operador Igualdad (==)
    int variable1 = 10;
    int variable2 = 20;
    boolean igualdad = variable1 == variable2;

    // Operador Desigualdad (!=)
    String color = "rojo";
    boolean noEsRojo = color != "rojo";

    // Operador Menor que (<)
    int valor1 = 5;
    int valor2 = 8;
    boolean menorQue = valor1 < valor2;

    // Operador Mayor que (>)
    float precio = 45.0;
    float limite = 50.0;
    boolean mayorQue = precio > limite;

    // Operador Menor o Igual que (<=)
    int cantidad = 10;
    int maximo = 15;
    boolean menorOIgualQue = cantidad <= maximo;

    // Operador Mayor o Igual que (>=)
    int edad = 18;
    int edadMinima = 21;
    boolean mayorOIgualQue = edad >= edadMinima;

    // Operador Suma (+)
    int numero1 = 5;
    int numero2 = 3;
    int suma = numero1 + numero2;

    // Operador Resta (-)
    float saldo = 100.0;
    float deuda = 25.0;
    float resta = saldo - deuda;

    // Operador Multiplicación (*)
    int factor1 = 4;
    int factor2 = 7;
    int multiplicacion = factor1 * factor2;

    // Operador División (/)
    float dividendo = 20.0;
    float divisor = 4.0;
    float division = dividendo / divisor;

    // Operador Módulo (%)
    int numero = 17;
    int modulo = numero % 3;

    // Operador Lógico NOT (!)
    boolean esVerdadero = true;
    boolean negacion = !esVerdadero; // negacion será false

    // Operador de Signo (+ o -) - Utilizado en expresiones numéricas
    int positivo = +5;  // positivo es igual a 5
    int negativo = -10; // negativo es igual a -10

}

class EjemplosWhile {
    static void main(String args) {
        var contador = 0;

        // Ejemplo de while con llaves
        while (contador < 5) {
            println("Contador con llaves: " + contador);
            contador = contador + 1;
        }

        // Ejemplo de while sin llaves (solo se ejecuta una instrucción)
        while (contador < 10)
            println("Contador sin llaves: " + contador);

        var numero = 10;

        // Ejemplo de while con llaves y condición inversa
        while (numero > 0) {
            println("Número con llaves: " + numero);
            numero = numero - 1;
        }

        // Ejemplo de while sin llaves y condición inversa
        while (numero > -5)
            println("Número sin llaves: " + numero);
    }
}
interface Interface4 {
    int methodInt();
}
interface Interface3 extends Interface4{
    void method();
    void method2();
    void method3();
}

class Class9 implements Interface3{
    void method(){}
    void method2(){}
    static void method3(){}
    int methodInt(){}
}

