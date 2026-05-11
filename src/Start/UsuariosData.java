package Start;

import java.io.*;
import Funciones.NodoUsuario;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;

public class UsuariosData {
    private static NodoUsuario cabeza = null;
    private static final String ARCHIVO = "usuarios.txt";
    static {
        cargarUsuarios();
    }
    public static void cargarUsuarios() {
        cabeza = null;
        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            NodoUsuario ultimo = null;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length < 2) continue;
                NodoUsuario nuevo = new NodoUsuario(partes[0], partes[1]);
                if (cabeza == null) {
                    cabeza = nuevo;
                    ultimo = nuevo;
                } else {
                    ultimo.sig = nuevo;
                    nuevo.ant = ultimo;
                    ultimo = nuevo;
                }
            }
            br.close();
        } catch (Exception e) {
            mostrarError("Error cargando usuarios");
        }
    }
    public static void guardarUsuario(String usuario, String contraseña) {
        NodoUsuario nuevo = new NodoUsuario(usuario, contraseña);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoUsuario actual = cabeza;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;
            nuevo.ant = actual;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true));
            bw.write(usuario + "|" + contraseña);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            mostrarError("Error guardando usuario");
        }
    }
    public static boolean validar(String usuario, String contraseña) {
    NodoUsuario actual = cabeza;
    while (actual != null) {
        if (actual.usuario != null && actual.contraseña != null) {
            if (actual.usuario.equals(usuario)
                    && actual.contraseña.equals(contraseña)) {
                return true;
            }
        }
        actual = actual.sig;
    }
    return false;
}

    
    private static void mostrarError(String mensaje) {

        System.out.println(mensaje);

        try {
            AudioClip sonido = new AudioClip(
                RegistroFXController.class
                    .getResource("/Sonidos/Win11 Error.mp3")
                    .toExternalForm()
            );
            sonido.play();
        } catch (Exception e) {}

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensaje");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}