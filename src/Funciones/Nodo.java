package Funciones;

public class Nodo {
    public Productos producto;
    public Nodo siguiente;

    public Nodo(Productos producto) {
        this.producto = producto;
        this.siguiente = null;
    }
}