package Funciones;

public class NodoCompra {
    public Compra compra;
    public NodoCompra siguiente;

    public NodoCompra(Compra compra) {
        this.compra = compra;
        this.siguiente = null;
    }
}
