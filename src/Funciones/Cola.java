package Funciones;

public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }
    
    public void encolar(Productos producto) {
        Nodo nuevo = new Nodo(producto);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }
    public Productos desencolar() {
        if (estaVacia()) return null;
            Productos producto = frente.producto;
            frente = frente.siguiente;
        if (frente == null) fin = null;
        tamanio--;
        return producto;
    }

    public Productos verFrente() {
        if (estaVacia()) return null;
            return frente.producto;
    }
    public boolean estaVacia() {
        return frente == null;
    }
    public int getTamanio() {
        return tamanio;
    }
    
    public java.util.List<Productos> obtenerTodos() {
        java.util.List<Productos> lista = new java.util.ArrayList<>();
        Nodo actual = frente;

        while (actual != null) {
            lista.add(actual.producto);
            actual = actual.siguiente;
        }
        return lista;
    }
    public void eliminarPorIndice(int indice) {
        if (indice < 0 || indice >= tamanio) return;
            if (indice == 0) {
                desencolar();
                return;
            }
        Nodo actual = frente;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }
        actual.siguiente = actual.siguiente.siguiente;

        if (actual.siguiente == null) {
            fin = actual;
        }
        tamanio--;
    }
    
    
    
    public void eliminarProducto(int id) {
        while (frente != null && frente.producto.getId() == id) {
            frente = frente.siguiente;
            tamanio--;
        }
        Nodo actual = frente;
        while (actual != null && actual.siguiente != null) {
            if (actual.siguiente.producto.getId() == id) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
            } else {
                actual = actual.siguiente;
            }
        }
        if (frente == null) {
            fin = null;
        }
    }
}