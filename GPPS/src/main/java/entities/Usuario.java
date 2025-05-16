package entities;

public abstract class Usuario {
	private static final String EL_NOMBRE_DE_USUARIO_EN_BLANCO = "El nombre de usuario no puede estar en blanco.";
	private static final String EL_CORREO_EN_BLANCO = "El correo no puede estar en blanco.";
	private static final String CONTRASENIA_EN_BLANCO = "La contraseña  no puede estar en blanco.";
	private String nombreUsuario;
	private String correo;
	private String contrasenia;

	public Usuario(String nombreUsuario, String correo, String contrasenia) {
		this.checkNombreUsuario(nombreUsuario);
		this.checkCorreo(correo);
		this.checkContrasenia(contrasenia);
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
	}

	public String obtenerNombreUsuario() {
		return this.nombreUsuario;
	}

	public String obtenerCorreo() {
		return this.correo;
	}

	private void checkNombreUsuario (String nombreUsuario){
		if (nombreUsuario.isBlank()){
			throw new RuntimeException(EL_NOMBRE_DE_USUARIO_EN_BLANCO);
		}
	}

	private void checkCorreo (String correo){
		if (correo.isBlank()){
			throw new RuntimeException(EL_CORREO_EN_BLANCO);
		}
	}

	private void checkContrasenia(String contraseña){
		if (contraseña.isBlank()){
			throw new RuntimeException(CONTRASENIA_EN_BLANCO);
		}
	}
}
