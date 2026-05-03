
public class Compra {
    Carrito productos;

    public Compra(Carrito carrito) {
        productos = new Carrito();
        carrito.copiarA(productos);
        
       Datos.listaCompras.add(this);
    }

    public String mostrarCompra(int numero) {
        return "Compra " + numero + ":\n" + productos.mostrar() + "\n\n";
    }
}
