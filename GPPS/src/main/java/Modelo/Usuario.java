package Modelo;

public abstract class Usuario {
    static final String NOMBRE_USUARIO_VACIO = "El nombre de usuario no puede estar vacío.";
    static final String CONTRASENIA_VACIA = "la contraseña no puede estar vacía.";
    static final String CORREO_VACIO = "El correo no puede estar vacío.";
    private String nombreUsuario;
    private String correo;
    private String contrasenia;

    public Usuario(String nombreUsuario, String correo, String contrasenia) {
        this.checkNombreUsuario(nombreUsuario);
        this.checkCorreo(correo);
        this.checkContrasenia(contrasenia);
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    private void checkNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.isBlank()) {
            throw new RuntimeException(NOMBRE_USUARIO_VACIO);
        }
    }

    private void checkCorreo(String correo) {
        if (correo.isBlank()) {
            throw new RuntimeException(CORREO_VACIO);
        }
    }

    private void checkContrasenia(String contrasenia) {
        if (contrasenia.isBlank()) {
            throw new RuntimeException(CONTRASENIA_VACIA);
        }
    }

}
