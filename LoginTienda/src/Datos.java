
import java.util.ArrayList;

public class Datos {
   public static ArrayList<Producto> listaProductos = new ArrayList<>();
   public static ArrayList<Compra> listaCompras = new ArrayList<>();


    static {
        listaProductos.add(new Producto("Laptop", 2000));
        listaProductos.add(new Producto("Mouse", 50));
        listaProductos.add(new Producto("telefono", 200));
        listaProductos.add(new Producto("tablet", 300));
        listaProductos.add(new Producto("cpu", 2000));
        listaProductos.add(new Producto("teclado", 100));
    }
    
    public static void eliminarProducto(String nombre) {
    for (int i = 0; i < listaProductos.size(); i++) {
        if (listaProductos.get(i).nombre.equalsIgnoreCase(nombre)) {
            listaProductos.remove(i);
            return;
        }
    }
}

}

