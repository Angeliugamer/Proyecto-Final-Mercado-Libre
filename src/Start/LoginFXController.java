package Start;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class LoginFXController {
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
    private void iniciar() throws Exception {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();
        if (user.equals("admin") && pass.equals("123")) {
            Parent root = FXMLLoader.load(
                getClass().getResource("/Start/admin/Admin.fxml")
            );
            Stage stage = (Stage) overlay.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setWidth(1117);
            stage.setHeight(560);

            stage.centerOnScreen();

            stage.show();
            return;
        }
        if (UsuariosData.validar(user, pass)) {
            Parent root = FXMLLoader.load(
                getClass().getResource("/Funciones/Venta.fxml")
            );
            Stage stage = (Stage) overlay.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            AudioClip sonido = new AudioClip(
                RegistroFXController.class.getResource("/Sonidos/Win11 Error.mp3").toExternalForm()
            );
            sonido.play();
            
            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("Mensaje");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario o contraseña incorrectos");

            alerta.showAndWait();
        }
    }
}
