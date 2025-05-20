package entities;

public class TutorExterno extends Persona {
    private static final String NOMBRE_DE_ENTIDAD_VACIO = "El nombre de la entidad no puede estar vacío.";
    private String nombreEntidad;

    public TutorExterno(String nombreUsuario, String correo, String contrasenia, String nombre, String apellido, String dni, String nombreEntidad) {
        super(nombreUsuario, correo, contrasenia, nombre, apellido, dni);
        this.checkNombreEntidad(nombreEntidad);
        this.nombreEntidad = nombreEntidad;
    }

    private void checkNombreEntidad(String nombreEntidad) {
        if (nombreEntidad.isBlank()) {
            throw new RuntimeException(NOMBRE_DE_ENTIDAD_VACIO);
        }
    }
}
