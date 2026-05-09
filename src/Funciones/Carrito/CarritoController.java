package Funciones.Carrito;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CarritoController {
    @FXML
    private Pane overlay;
    @FXML
    private ImageView iconMoon;
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
    }
}
