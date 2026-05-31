package Funciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Compra {
    private String usuario;
    private List<Productos> productos;
    private double total;
    private String fecha;

    public Compra(String usuario, List<Productos> productos, double total) {
        this.usuario = usuario;
        this.productos = productos;
        this.total = total;
        this.fecha = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    public String getUsuario() { return usuario; }
    public List<Productos> getProductos() { return productos; }
    public double getTotal() { return total; }
    public String getFecha() { return fecha; }

    public String toTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append(usuario).append("|")
          .append(fecha).append("|")
          .append(String.format("%.0f", total)).append("|");
        for (Productos p : productos) {
            sb.append(p.getNombre()).append(",");
        }
        return sb.toString();
    }
}