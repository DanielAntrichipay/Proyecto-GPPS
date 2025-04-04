package entities;

public class Rol {
	private String nombre;

	public Rol(String nombre) {
		this.nombre = nombre;
	}

	public String consultarRol() {
		return this.nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
}
