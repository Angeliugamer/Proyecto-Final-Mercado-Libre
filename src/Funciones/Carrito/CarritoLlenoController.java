package Funciones.Carrito;

import Funciones.Cola;
import Funciones.Productos;
import Funciones.VentaController;
import Funciones.Compra;
import Funciones.PilaCompras;

import Start.ConfiguracionGeneral;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.util.List;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CarritoLlenoController {
    @FXML private Pane overlay;
    @FXML private ImageView iconMoon;

    @FXML private Text txtNombre1;
    @FXML private Text txtPrecio1;
    @FXML private Text txtNombre2;
    @FXML private Text txtPrecio2;
    @FXML private Text txtNombre3;
    @FXML private Text txtPrecio3;

    @FXML private Text txtListaResumen;
    @FXML private Text txtTotalResumen;
    @FXML private Text txtPagina;
    
    @FXML private Pane slot1;
    @FXML private Pane slot2;
    @FXML private Pane slot3;
    
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
        txtPrecio1.setTextAlignment(TextAlignment.RIGHT);
        txtPrecio2.setTextAlignment(TextAlignment.RIGHT);
        txtPrecio3.setTextAlignment(TextAlignment.RIGHT);
        
        txtListaResumen.setTextAlignment(TextAlignment.RIGHT);
        txtTotalResumen.setTextAlignment(TextAlignment.RIGHT);
    }
    
    
    
    private Cola carrito = ConfiguracionGeneral.carritoGlobal;
    private List<Productos> lista;
    private int paginaActual = 0;
    private static final int POR_PAGINA = 3;

    public void setCarrito(Cola carrito) {
        this.carrito = carrito;
        this.lista = carrito.obtenerTodos();
        paginaActual = 0;
        mostrarPagina();
    }
    private void mostrarPagina() {
        Text[] nombres = {txtNombre1, txtNombre2, txtNombre3};
        Text[] precios = {txtPrecio1, txtPrecio2, txtPrecio3};
        Pane[] slots = {slot1, slot2, slot3};

        int inicio = paginaActual * POR_PAGINA;

        for (int i = 0; i < POR_PAGINA; i++) {
            int indice = inicio + i;
            if (indice < lista.size()) {
                slots[i].setVisible(true);
                nombres[i].setText(lista.get(indice).getNombre());
                precios[i].setText(String.format("$%,.0f", lista.get(indice).getPrecio()));
            } else {
                slots[i].setVisible(false);
                nombres[i].setText("");
                precios[i].setText("");
            }
        }

        double total = 0;
        StringBuilder resumen = new StringBuilder();
        for (Productos p : lista) {
            total += p.getPrecio();
            resumen.append(p.getNombre()).append("\n");
        }
        txtListaResumen.setText(resumen.toString());
        txtTotalResumen.setText("TOTAL: $" + String.format("%,.0f", total));

        int totalPaginas = (int) Math.ceil((double) lista.size() / POR_PAGINA);
        txtPagina.setText((paginaActual + 1) + " / " + totalPaginas);
    }
    @FXML
    private void paginaSiguiente() {
        int totalPaginas = (int) Math.ceil((double) lista.size() / POR_PAGINA);
        if (paginaActual + 1 < totalPaginas) {
            paginaActual++;
            mostrarPagina();
        }
    }
    @FXML
    private void paginaAnterior() {
        if (paginaActual > 0) {
            paginaActual--;
            mostrarPagina();
        }
    }
    @FXML
    private void volverTienda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/Funciones/Venta.fxml")
            );
            Parent root = loader.load();
            VentaController vc = loader.getController();
            vc.setCarrito(carrito);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    private void eliminarProducto1() {
        eliminarSlot(0);
    }
    @FXML
    private void eliminarProducto2() {
        eliminarSlot(1);
    }
    @FXML
    private void eliminarProducto3() {
        eliminarSlot(2);
    }

    private void eliminarSlot(int slot) {
        int indice = paginaActual * POR_PAGINA + slot;
        if (indice < lista.size()) {
            carrito.eliminarPorIndice(indice);
            lista = carrito.obtenerTodos(); 
            
            int totalPaginas = (int) Math.ceil((double) lista.size() / POR_PAGINA);
            if (paginaActual >= totalPaginas && paginaActual > 0) {
                paginaActual--;
            }
            if (carrito.estaVacia()) {
                volverCarritoVacio();
                return;
            }
            mostrarPagina();
        }
    }
    private void volverCarritoVacio() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/Funciones/Carrito/Carrito.fxml")
            );
            Parent root = loader.load();
            CarritoController cc = loader.getController();
            cc.setCarrito(carrito);

            Stage stage = (Stage) slot1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void realizarCompra(ActionEvent event) {
        try {
            List<Productos> lista = carrito.obtenerTodos();
            double total = 0;
            for (Productos p : lista) total += p.getPrecio();

            Compra compra = new Compra(
                ConfiguracionGeneral.usuarioActual,
                lista,
                total
            );
            ConfiguracionGeneral.historialCompras.apilar(compra);
            
            File archivo = new File("compras.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(compra.toTexto());
            bw.newLine();
            bw.close();
            System.out.println("Compra guardada: " + compra.toTexto());
            
            while (!carrito.estaVacia()) {
                carrito.desencolar();
            }
            volverTienda(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
