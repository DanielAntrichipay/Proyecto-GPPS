package backend.dto;

public class UsuarioDTO {
	private String legajo;
	private String nombreCompleto;
	private String rol;

	public UsuarioDTO(String legajo, String nombreCompleto, String rol) {
		this.legajo = legajo;
		this.nombreCompleto = nombreCompleto;
		this.rol = rol;
	}

	public String getLegajo() {
		return legajo;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getRol() {
		return rol;
	}
}
