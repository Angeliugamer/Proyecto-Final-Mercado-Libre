package Funciones;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VentaController{
    @FXML
    private Pane overlay;
    @FXML
    private ImageView iconMoon;
    private boolean modoNoche = false;
    @FXML
    private void cambiarModo() {
        if (!modoNoche) {
            // ACTIVAR modo noche
            overlay.setOpacity(0.3); // oscurece

            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/Sun.png").toExternalForm()
            ));

            modoNoche = true;
        } else {
            // VOLVER a modo día
            overlay.setOpacity(0.0);

            iconMoon.setImage(new Image(
                getClass().getResource("/img/Icons/MoonStars.png").toExternalForm()
            ));

            modoNoche = false;
        }
    }
}

