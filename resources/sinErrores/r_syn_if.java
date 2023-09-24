///[SinErrores]
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
