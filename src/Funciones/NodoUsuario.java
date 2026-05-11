package Funciones;

public class NodoUsuario {

    public String usuario;
    public String contraseña;

    public NodoUsuario sig;
    public NodoUsuario ant;

    public NodoUsuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;

        this.sig = null;
        this.ant = null;
    }
}
