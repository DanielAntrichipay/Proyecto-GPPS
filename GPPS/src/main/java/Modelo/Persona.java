package Modelo;

public abstract class Persona extends Usuario {
    static final String NOMBRE_EN_BLANCO = "El nombre no puede estar vacío";
    static final String APELLIDO_EN_BLANCO = "El nombre no puede estar vacío";
    static final String DNI_EN_BLANCO = "El nombre no puede estar vacío";
    private String nombre;
    private String apellido;
    private String dni;

    public Persona(String nombreUsuario, String correo, String contrasenia, String nombre, String apellido, String dni) {
        super(nombreUsuario, correo, contrasenia);
        this.checkNombre(nombre);
        this.checkApellido(apellido);
        this.checkDni(dni);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String obtenerNombreCompleto() {
        return this.apellido + ", " + this.nombre + ".";
    }

    public String consultarDNI() {
        return this.dni;
    }

    private void checkNombre(String nombre) {
        if (nombre.isBlank()) {
            throw new RuntimeException(NOMBRE_EN_BLANCO);
        }
    }

    private void checkApellido(String apellido) {
        if (nombre.isBlank()) {
            throw new RuntimeException(APELLIDO_EN_BLANCO);
        }
    }

    private void checkDni(String dni) {
        if (nombre.isBlank()) {
            throw new RuntimeException(DNI_EN_BLANCO);
        }
    }
}
