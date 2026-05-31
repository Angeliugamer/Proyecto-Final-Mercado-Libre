package Funciones;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Funciones.VentaController;


public class InfoController{
    @FXML private Pane overlay;
    @FXML private ImageView iconMoon;
    @FXML private Text txtNombre;
    @FXML private Text txtPrecio;
    @FXML private Text Cantidad;

    private Productos producto;
    private Cola carrito = ConfiguracionGeneral.carritoGlobal;
    private int cantidad = 1;
    
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
        if (ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/Sun.png").toExternalForm()
            ));
        }
        Cantidad.setText(cantidad + "");
    }
    @FXML
    private void volver(ActionEvent event) {
        volverTienda(event);
    }
    @FXML
    private void aumentar() {
        cantidad++;
        Cantidad.setText(cantidad + "");
    }
    @FXML
    private void disminuir() {
        if (cantidad > 1) {
            cantidad--;
            Cantidad.setText(cantidad + "");
        }
    }
   
    
    
    public void setProducto(Productos producto) {
        this.producto = producto;
        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.format("$%,.0f", producto.getPrecio()));
    }
    public void setCarrito(Cola carrito) {
        this.carrito = carrito;
    } 
    
    @FXML
    private void agregarCarrito(ActionEvent event) {
        if (producto != null && carrito != null) {
            for (int i = 0; i < cantidad; i++) {
                carrito.encolar(producto);
            }
            ConfiguracionGeneral.productosSeleccionados.add(
                producto.getId()
            );
            System.out.println(
                "Agregado al carrito: " + producto.getNombre()
            );
        }
        volverTienda(event);
    }
    @FXML
    private void comprarAhora(ActionEvent event) {
        if (producto != null && carrito != null) {
            carrito.encolar(producto);
        }
        volverTienda(event);
    }
    private void volverTienda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/Funciones/Venta.fxml")
            );
            Parent root = loader.load();

            VentaController vc = loader.getController();
            vc.setCarrito(carrito);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

