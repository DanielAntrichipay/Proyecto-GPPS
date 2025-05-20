package entities;

public class Entidad extends Usuario {
    static final String NOMBRE_VACIO = "El nombre no puede estar vacío";
    private String nombre;

    public Entidad(String nombreUsuario, String correo, String contrasenia, String nombre) {
        super(nombreUsuario, correo, contrasenia);
        this.checkNombre(nombre);
        this.nombre = nombre;
    }

    private void checkNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new RuntimeException(NOMBRE_VACIO);
        }
    }

    public String getNombre() {
        return nombre;
    }
}
