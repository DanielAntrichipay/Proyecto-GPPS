package entities;


public class Persona extends Usuario{
    static final String NOMBRE_EN_BLANCO = "El nombre no puede estar en blanco";
    static final String APELLIDO_EN_BLANCO = "El apellido no puede estar en blanco";
    private String nombre;
    private String apellido;
    private Rol rol;

    public Persona (String nombreDeUsuario, String correo, String contrasenia, String nombre, String apellido , Rol rol ){
        super (nombreDeUsuario, correo, contrasenia);
        this.checkNombre(nombre);
        this.checkApellido(apellido);
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }
    private void checkNombre(String nombre){
        if (nombre.isBlank()){
            throw new RuntimeException(NOMBRE_EN_BLANCO);
        }
    }
    private void checkApellido(String apellido){
        if (apellido.isBlank()){
            throw new RuntimeException(NOMBRE_EN_BLANCO);
        }
    }
}
