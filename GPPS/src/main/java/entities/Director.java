package entities;

public class Director extends Persona {
    private static final String LEGAJO_INVALIDO = "El legajo no puede estar en blanco o tener menos de 10 caracteres.";
    private String legajo;

    public Director(String nombreUsuario, String correo, String contrasenia, String nombre, String apellido, String dni, String legajo) {
        super(nombreUsuario, correo, contrasenia, nombre, apellido, dni);
        this.checkLegajo(legajo);
        this.legajo = legajo;
    }

    private void checkLegajo(String legajo) {
        if (legajo.isBlank() || legajo.length() == 10) {
            throw new RuntimeException(LEGAJO_INVALIDO);
        }
    }
}
