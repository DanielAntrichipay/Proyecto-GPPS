package entities;

public class Usuario {
	private Rol rol;
	private String legajo;
	private String nombre;
	private String apellido;

	public Usuario(String legajo, String nombre, String apellido, Rol rol) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}

	public String obtenerNombreCompeto() {
		return this.apellido + ", " + this.nombre;
	}

	public String consultarRol() {
		return this.rol.getNombre();
	}

	public String consultarLegajo() {
		return this.legajo;
	}

	public void cambiarNombreCompleto(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

}
