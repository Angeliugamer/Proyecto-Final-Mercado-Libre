package Start;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;

public class UsuariosData {
    public static ArrayList<String> usuarios = new ArrayList<>();
    public static ArrayList<String> contraseñas = new ArrayList<>();

    private static final String ARCHIVO = "usuarios.txt";
    static {
        cargarUsuarios();
    }
    public static void cargarUsuarios() {
        usuarios.clear();
        contraseñas.clear();
        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                usuarios.add(partes[0]);
                contraseñas.add(partes[1]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error cargando usuarios");
            AudioClip sonido = new AudioClip(
                RegistroFXController.class.getResource("/Sonidos/Win11 Error.mp3").toExternalForm()
            );
            sonido.play();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Mensaje");
            alerta.setHeaderText(null);
            alerta.setContentText("Error cargando usuarios");

            alerta.showAndWait();
        }
    }

    public static void guardarUsuario(String usuario, String contraseña) {
        usuarios.add(usuario);
        contraseñas.add(contraseña);
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(ARCHIVO, true)
            );
            bw.write(usuario + "|" + contraseña);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error guardando usuario");
            AudioClip sonido = new AudioClip(
                RegistroFXController.class.getResource("/Sonidos/Win11 Error.mp3").toExternalForm()
            );
            sonido.play();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

            alerta.setTitle("Mensaje");
            alerta.setHeaderText(null);
            alerta.setContentText("Error guardando usuario");

            alerta.showAndWait();
        }
    }
    public static boolean validar(String usuario, String contraseña) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).equals(usuario)
                    && contraseñas.get(i).equals(contraseña)) {
                return true;
            }
        }
        return false;
    }
}