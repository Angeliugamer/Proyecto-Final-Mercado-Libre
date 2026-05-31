package Funciones;

public class PilaCompras {
    private NodoCompra tope;
    private int tamanio;

    public PilaCompras() {
        tope = null;
        tamanio = 0;
    }
    public void apilar(Compra compra) {
        NodoCompra nuevo = new NodoCompra(compra);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }
    public Compra desapilar() {
        if (estaVacia()) return null;
        Compra compra = tope.compra;
        tope = tope.siguiente;
        tamanio--;
        return compra;
    }
    public boolean estaVacia() {
        return tope == null;
    }
    public int getTamanio() {
        return tamanio;
    }
}
