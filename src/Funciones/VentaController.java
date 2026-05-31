package Funciones;

import Funciones.Carrito.CarritoController;
import Funciones.Carrito.CarritoLlenoController;
import Funciones.InfoController;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VentaController{
    private List<Productos> productos = new ArrayList<>();
    private Cola carrito = new Cola();
    private int indiceActual = 0;
    @FXML private Pane overlay;
    @FXML private ImageView iconMoon;
    
    @FXML private Text TextList;
    
    @FXML private Label txtNombre1;
    @FXML private Label txtPrecio1;
    @FXML private Label txtNombre2;
    @FXML private Label txtPrecio2;
    @FXML private Label txtNombre3;
    @FXML private Label txtPrecio3;
    @FXML private Label txtNombre4;
    @FXML private Label txtPrecio4;
    
    @FXML private Button check1;
    @FXML private Button check2;
    @FXML private Button check3;
    @FXML private Button check4;
    
    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private ImageView img3;
    @FXML private ImageView img4;
    
    @FXML private ImageView imvCheck1;
    @FXML private ImageView imvCheck2;
    @FXML private ImageView imvCheck3;
    @FXML private ImageView imvCheck4;
    
    @FXML private Pane ProductPane1;
    @FXML private Pane ProductPane2;
    @FXML private Pane ProductPane3;
    @FXML private Pane ProductPane4;
    
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
    
    
    
    private void toggleCheck(int index, ImageView imagen) {
        int indice = indiceActual + index;

        if (indice >= productos.size()) {
        return;
        }
        Productos p = productos.get(indice);

        if (ConfiguracionGeneral.productosSeleccionados.contains(p.getId())) {
            ConfiguracionGeneral.productosSeleccionados.remove(p.getId());
            carrito.eliminarProducto(p.getId());
            imagen.setImage(
                new Image(
                    getClass().getResourceAsStream(
                        "/img/Icons/TagSimple.png"
                    )
                )
            );
        } else {
            ConfiguracionGeneral.productosSeleccionados.add(p.getId());
            carrito.encolar(p);
            imagen.setImage(
                new Image(
                    getClass().getResourceAsStream(
                        "/img/Icons/TagSimpleVerde.png"
                    )
                )
            );
        }
    }
    @FXML
    private void toggleCheck1() {
        toggleCheck(0, imvCheck1);
    }
    @FXML
    private void toggleCheck2() {
        toggleCheck(1, imvCheck2);
    }
    @FXML
    private void toggleCheck3() {
        toggleCheck(2, imvCheck3);
    }
    @FXML
    private void toggleCheck4() {
        toggleCheck(3, imvCheck4);
    }
    
    
    
    @FXML
    private void salir(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar salida");
            alerta.setHeaderText("¿Deseas cerrar sesión?");
            alerta.setContentText("Se perderán los cambios no guardados.");
        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No");
            alerta.getButtonTypes().setAll(si, no);
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == si) {
            try {
                Parent root = FXMLLoader.load(
                    getClass().getResource("/Start/LoginFX.fxml")
                );
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setCarrito(Cola carrito) {
        this.carrito = carrito;
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
            ProductPane1, ProductPane2, ProductPane3, ProductPane4
        };
        ImageView[] imvChecks = {
            imvCheck1, imvCheck2, imvCheck3, imvCheck4
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
                
                if (ConfiguracionGeneral.productosSeleccionados.contains(p.getId())) {
                    imvChecks[i].setImage(
                        new Image(
                            getClass().getResourceAsStream(
                                "/img/Icons/TagSimpleVerde.png"
                            )
                        )
                    );
                } else {
                    imvChecks[i].setImage(
                        new Image(
                            getClass().getResourceAsStream(
                                "/img/Icons/TagSimple.png"
                            )
                        )
                    );
                }
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
    
    
    
    @FXML
    private void verCarrito(ActionEvent event) {
        try {
            if (carrito.estaVacia()) {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Funciones/Carrito/Carrito.fxml")
                );
                Parent root = loader.load();
                CarritoController cc = loader.getController();
                cc.setCarrito(carrito);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Funciones/Carrito/CarritoLleno.fxml")
                );
                Parent root = loader.load();
                CarritoLlenoController cc = loader.getController();
                cc.setCarrito(carrito);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void abrirInfo(ActionEvent event, int slot) {
        int indice = indiceActual + slot;
        if (indice >= productos.size()) return;
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/Funciones/Info.fxml")
            );
            Parent root = loader.load();

            InfoController ic = loader.getController();
            ic.setProducto(productos.get(indice));
            ic.setCarrito(carrito);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    private void verInfo1(ActionEvent event) { 
        abrirInfo(event, 0); 
    }
    @FXML 
    private void verInfo2(ActionEvent event) { 
        abrirInfo(event, 1); 
    }
    @FXML 
    private void verInfo3(ActionEvent event) { 
        abrirInfo(event, 2); 
    }
    @FXML 
    private void verInfo4(ActionEvent event) { 
        abrirInfo(event, 3); 
    }
    public void eliminarProducto(int id) {
    }
    @FXML
    private void continuarCompra(ActionEvent event) {
        System.out.println("Continuar compra - pendiente");
    }
    
    
    
    private void guardarProductos() { //OLD
        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.txt"))) {
            for (Productos p : productos) {
                pw.println(p.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void agregarProducto(Productos p) { //OLD
        productos.add(p);
        guardarProductos();
        actualizarLista();
    }
    public void eliminarProductoOLD(int id) {
        productos.removeIf(p -> p.getId() == id);
        guardarProductos();
        actualizarLista();
        if (indiceActual >= productos.size()) {
            indiceActual = productos.size() - 1;
        }
        mostrarProducto();
    }
    private void actualizarListaOLD() {
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