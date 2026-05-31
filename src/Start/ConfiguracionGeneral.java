package Start;

import Funciones.Cola;
import Funciones.PilaCompras;

import java.util.Set;
import java.util.HashSet;

public class ConfiguracionGeneral {
    public static boolean modoNoche = false;
    public static String usuarioActual = "";
    public static PilaCompras historialCompras = new PilaCompras();
    
    public static Set<Integer> productosSeleccionados = new HashSet<>();
    public static Cola carritoGlobal = new Cola();
}