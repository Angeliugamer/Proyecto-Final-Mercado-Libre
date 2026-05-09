package Start;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFXController {
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
     @FXML
    private void abrirRegistro() throws Exception {

        Parent root = FXMLLoader.load(
            getClass().getResource("/Start/RegistroFX.fxml")
        );

        Stage stage = (Stage) overlay.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void iniciar() {

    }
}
