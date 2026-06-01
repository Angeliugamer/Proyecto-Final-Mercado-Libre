package Start.admin;

import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistorialFXController implements Initializable {
    @FXML private ListView<String> listaHistorial;
    @FXML private Pane overlay;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarHistorial();

        if (Start.ConfiguracionGeneral.modoNoche) {
            overlay.setOpacity(0.3);
        }
    }
    
    private void cargarHistorial() {
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            File archivo = new File("compras.txt");
            if (!archivo.exists()) {
                items.add("No hay compras registradas");
                listaHistorial.setItems(items);
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    items.add(linea);
                }
            }
            br.close();
            if (items.isEmpty()) {
                items.add("No hay compras registradas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            items.add("Error al cargar historial");
        }
        listaHistorial.setItems(items);
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