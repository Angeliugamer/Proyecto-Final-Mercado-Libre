package Funciones;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentaController{
    private List<Productos> productos = new ArrayList<>();
    private int indiceActual = 0;
    @FXML
    private Pane overlay;
    @FXML
    private ImageView iconMoon;
    
    @FXML
    private Text TextList;
    
    @FXML
    private Label txtNombre1;
    @FXML
    private Label txtPrecio1;
    @FXML
    private Label txtNombre2;
    @FXML
    private Label txtPrecio2;
    @FXML
    private Label txtNombre3;
    @FXML
    private Label txtPrecio3;
    @FXML
    private Label txtNombre4;
    @FXML
    private Label txtPrecio4;
    
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    
    @FXML
    private Pane ProductPane1;
    @FXML
    private Pane ProductPane2;
    @FXML
    private Pane ProductPane3;
    @FXML
    private Pane ProductPane4;
    
    @FXML
    private void cambiarModo() {
        if (!ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/Sun.png").toExternalForm()
            ));
            ConfiguracionGeneral.modoNoche = true;
        } else {
            overlay.setOpacity(0.0);
            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/MoonStars.png").toExternalForm()
            ));
            ConfiguracionGeneral.modoNoche = false;
        }
    }
    @FXML
    public void initialize() {
        cargarProductos();
        actualizarLista();
        mostrarProducto();
        if (ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/Sun.png").toExternalForm()
            ));
        }
    }
    @FXML
    private void cargarProductos() {
        productos.clear();
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String descripcion = partes[2];
                double precio = Double.parseDouble(partes[3]);
                String imagen = partes[4];

                productos.add(
                    new Productos(id, nombre, descripcion, precio, imagen)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void mostrarProducto() {
        Label[] nombres = {
            txtNombre1, txtNombre2, txtNombre3, txtNombre4
        };
        Label[] precios = {
            txtPrecio1, txtPrecio2, txtPrecio3, txtPrecio4
        };
        ImageView[] imagenes = {
            img1, img2, img3, img4
        };
        Pane[] paneles = {
            ProductPane1,
            ProductPane2,
            ProductPane3,
            ProductPane4
        };

        for (int i = 0; i < 4; i++) {
            int indice = indiceActual + i;
            if (indice < productos.size()) {
                Productos p = productos.get(indice);
                
                paneles[i].setVisible(true);

                nombres[i].setText(p.getNombre());
                precios[i].setText(String.format("$%,.0f", p.getPrecio()));

                Image img = new Image(
                    getClass().getResourceAsStream("/img/productos/" + p.getImagen())
                );

                imagenes[i].setImage(img);
            } else {
                paneles[i].setVisible(false);
            }
        }
    }
    @FXML
    private void siguienteProducto() {
        if (productos.isEmpty()) return;
        if (indiceActual + 4 < productos.size()) {
            indiceActual += 4;
            mostrarProducto();
        }
    }
    @FXML
    private void anteriorProducto() {
        if (productos.isEmpty()) return;
        if (indiceActual - 4 >= 0) {
            indiceActual -= 4;
            mostrarProducto();
        }
    }
    private void guardarProductos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.txt"))) {
            for (Productos p : productos) {
                pw.println(p.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void agregarProducto(Productos p) {
        productos.add(p);
        guardarProductos();
        actualizarLista();
    }
    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
        guardarProductos();
        actualizarLista();
        if (indiceActual >= productos.size()) {
            indiceActual = productos.size() - 1;
        }
        mostrarProducto();
    }
    private void actualizarLista() {

        StringBuilder lista = new StringBuilder();

        for (Productos p : productos) {

            lista.append(p.getId())
                 .append(" - ")
                 .append(p.getNombre())
                 .append("\n");

        }

        TextList.setText(lista.toString());
    }
}