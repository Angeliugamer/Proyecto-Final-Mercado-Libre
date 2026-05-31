package Funciones.Carrito;

import Funciones.VentaController;
import Funciones.Cola;
import Funciones.Productos;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.util.List;

public class CarritoController {
    @FXML private Text txtMensaje;
    @FXML private Pane overlay;
    @FXML private ImageView iconMoon;
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
        Button btnVolver = new Button("VOLVER TEST");
        btnVolver.setLayoutX(20);
        btnVolver.setLayoutY(510);
        btnVolver.setOnAction(e -> {
            System.out.println("BOTON PROGRAMATICO PRESIONADO");
        });
        mainPane.getChildren().add(btnVolver);
    
        if (ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/Sun.png").toExternalForm()
            ));
        }
    }
    
    
    
    @FXML
    private Pane mainPane;
    private Cola carrito = ConfiguracionGeneral.carritoGlobal;

    public void setCarrito(Cola carrito) {
        this.carrito = carrito;
        mostrarCarrito();
    }
    private void mostrarCarrito() {
        if (carrito == null || carrito.estaVacia()) {
           txtMensaje.setText("No hay productos en el carrito");
           return;
        }
        List<Productos> lista = carrito.obtenerTodos();
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (Productos p : lista) {
            sb.append("• ")
              .append(p.getNombre())
              .append("  -  $")
              .append(String.format("%,.0f", p.getPrecio()))
              .append("\n");
            total += p.getPrecio();
        }
        sb.append("\n")
          .append("TOTAL: $")
          .append(String.format("%,.0f", total));

        txtMensaje.setText(sb.toString());
    }
    @FXML
    private void continuarCompra(ActionEvent event) {
        System.out.println("BOTON PRESIONADO");
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

