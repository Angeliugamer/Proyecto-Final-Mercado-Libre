package Start.admin;

import Funciones.Productos;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;


public class AdminController {
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtImagen;
    @FXML private TextField txtCategoria;
    @FXML private ListView<String> listaProductos;
   
    private ObservableList<String> items =
    FXCollections.observableArrayList();

    @FXML private Pane overlay;
    @FXML private ImageView iconMoon;

    @FXML
    private void cambiarModo() {
        if (!Start.ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(
                new javafx.scene.image.Image(
                    getClass()
                    .getResource("/img/Icons/Sun.png")
                    .toExternalForm()
                )
            );
            Start.ConfiguracionGeneral.modoNoche = true;
        } else {
        overlay.setOpacity(0.0);
            iconMoon.setImage(
                new javafx.scene.image.Image(
                    getClass()
                    .getResource("/img/Icons/MoonStars.png")
                    .toExternalForm()
                )
            );
            Start.ConfiguracionGeneral.modoNoche = false;
        }
    }
    @FXML
    public void initialize() {
       cargarProductos();
        if (Start.ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(
                new javafx.scene.image.Image(
                    getClass()
                    .getResource("/img/Icons/Sun.png")
                    .toExternalForm()
                )
            );
        }
    }

    
    
    @FXML
    private void agregarProducto() {
        try {
            if (
                txtId.getText().isEmpty() ||
                txtNombre.getText().isEmpty() ||
                txtPrecio.getText().isEmpty() ||
                txtImagen.getText().isEmpty()
            ) {
                mostrarMensaje("Complete todos los campos");
                return;
            }
            File archivo = new File("productos.txt");
            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(archivo, true)
                );
            String linea =
                txtId.getText() + ";" +
                txtNombre.getText() + ";" +
                txtDescripcion.getText() + ";" +
                txtPrecio.getText() + ";" +
                txtImagen.getText();
            bw.write(linea);
            bw.newLine();
            bw.close();

            mostrarMensaje("Producto agregado correctamente");
            limpiarCampos();
            cargarProductos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al agregar producto");
        }
    }
    @FXML
    private void eliminarProducto() {
        String seleccionado =
            listaProductos.getSelectionModel()
            .getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje(
                "Seleccione un producto"
            );
            return;
        }
        try {
            File archivo = new File("productos.txt");
            File temporal = new File("temp.txt");
            BufferedReader br =
                new BufferedReader(
                    new FileReader(archivo)
                );
            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(temporal)
                );
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.equals(seleccionado)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();

            archivo.delete();
            temporal.renameTo(archivo);
            cargarProductos();
            mostrarMensaje(
                "Producto eliminado"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void cargarProductos() {
        items.clear();
        try {
            BufferedReader br =
                new BufferedReader(
                    new FileReader("productos.txt")
                );
            String linea;
            while ((linea = br.readLine()) != null) {
                items.add(linea);
            }
            br.close();
            listaProductos.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtImagen.clear();
    }
    private void mostrarMensaje(String mensaje) {
        Alert alert =
            new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void salir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/Start/LoginFX.fxml")
            );
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setWidth(680);  
            stage.setHeight(630);  
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verHistorial(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/Start/admin/HistorialFX.fxml")
            );
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setWidth(1117);
            stage.setHeight(560);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}