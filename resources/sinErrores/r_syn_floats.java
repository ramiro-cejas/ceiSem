///[SinErrores]
class Class {
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