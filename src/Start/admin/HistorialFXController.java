package Start.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HistorialFXController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void volverAdmin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/Start/admin/Admin.fxml")
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setWidth(1117);
            stage.setHeight(560);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}