package Start;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;

public class RegistroFXController {
    @FXML
    private Pane overlay;
    @FXML
    private ImageView iconMoon;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
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
    private void regresar() throws Exception {
        Parent root = FXMLLoader.load(
            getClass().getResource("/Start/LoginFX.fxml")
        );

        Stage stage = (Stage) overlay.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void registrar() throws Exception {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            AudioClip sonido = new AudioClip(
                RegistroFXController.class.getResource("/Sonidos/Win11 Error.mp3").toExternalForm()
            );
            sonido.play();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Mensaje");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario y/o Contraseña vacia");

            alerta.showAndWait();
            return;
        }

        Start.UsuariosData.guardarUsuario(user, pass);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Mensaje");
        alerta.setHeaderText(null);
        alerta.setContentText("Usuario registrado");

        alerta.showAndWait();

        Parent root = FXMLLoader.load(
            getClass().getResource("/Start/LoginFX.fxml")
        );

        Stage stage = (Stage) overlay.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
