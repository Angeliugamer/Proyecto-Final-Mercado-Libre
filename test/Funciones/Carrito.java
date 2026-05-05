package Funciones;


public class Carrito {
    Nodo cabeza;

   
    public void agregar(Producto p) {
        Nodo nuevo = new Nodo(p);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

   
    public String mostrar() {
    String texto = "";
    Nodo aux = cabeza;
    double suma = 0;

    while (aux != null) {
        texto += aux.dato.nombre + " - $" + aux.dato.precio + "\n";
        suma += aux.dato.precio;
        aux = aux.siguiente;
    }

    texto += "\nTOTAL: $" + suma;

    return texto;
}
    public double total() {
    double suma = 0;
    Nodo aux = cabeza;

    while (aux != null) {
        suma += aux.dato.precio;
        aux = aux.siguiente;
    }

    return suma;
}
    public void eliminar(String nombre) {
    if (cabeza == null) {
        return;
    }

   
    if (cabeza.dato.nombre.equals(nombre)) {
        cabeza = cabeza.siguiente;
        return;
    }

    Nodo actual = cabeza;
    Nodo anterior = null;

    while (actual != null && !actual.dato.nombre.equals(nombre)) {
        anterior = actual;
        actual = actual.siguiente;
    }

    if (actual != null) {
        anterior.siguiente = actual.siguiente;
    }
}
public void vaciar() {
    cabeza = null;
}
public void copiarA(Carrito destino) {
    Nodo aux = cabeza;

    while (aux != null) {
        destino.agregar(new Producto(aux.dato.nombre, aux.dato.precio));
        aux = aux.siguiente;
    }
}


}

