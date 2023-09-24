///[SinErrores]
// Clase genérica que toma un tipo T
class MiClase<T> {
    T atributo;

    public MiClase(T valor) {
        this.atributo = valor;
    }

    T getAtributo() {
        return atributo;
    }

    // Método genérico que toma una lista de cualquier tipo
    static <E> void imprimirLista(List<E> lista) {
        var i = 0;
        while (i < lista.size()) {
            var elemento = lista.get(i);
            i = i + 1;
        }
    }

    // Método genérico que toma un mapa con claves y valores de tipos diferentes
    static <K, V> void imprimirMapa(Map<K, V> mapa) {
        var iterator = mapa.entrySet().iterator();
        while (iterator.hasNext()) {
            entry = iterator.next();
        }
    }
}

class EjemploGenericidad {
    static void main(String args) {
        // Uso de la clase genérica MiClase
        var miObjeto = new MiClase<>("Hola, mundo");

        // Uso de métodos genéricos
        var listaEnteros = new ArrayList<>();
        listaEnteros.add(1);
        listaEnteros.add(2);
        listaEnteros.add(3);
        MiClase.imprimirLista(listaEnteros);

        var mapaPrecios = new HashMap<>();
        mapaPrecios.put("Producto A", 19.99);
        mapaPrecios.put("Producto B", 29.99);
        mapaPrecios.put("Producto C", 9.99);
        MiClase.imprimirMapa(mapaPrecios);
    }
}
