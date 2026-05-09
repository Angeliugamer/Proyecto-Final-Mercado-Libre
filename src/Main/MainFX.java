package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/Start/LoginFX.fxml")
        );
        stage.getIcons().add(
            new Image(getClass().getResourceAsStream("/img/Icons/1IconoApp.png"))
        );

        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Mercado Libre");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}