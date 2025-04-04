import org.junit.jupiter.api.Test;

import entities.Rol;
import entities.Usuario;
import commons.UsuarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
	@Test
	public void creacionDeUsuarioTest () {
		var rol = new Rol (UsuarioEnum.ESTUDIANTE.name());
		var usuario = new Usuario ("UNRN-123456", "Jose Martin", "Momoa", rol);

		assertEquals ("Momoa, Jose Martin", usuario.obtenerNombreCompeto());
		assertEquals ("UNRN-123456", usuario.consultarLegajo());
		assertEquals (rol.getNombre(), usuario.consultarRol());
		
	}
	@Test
	public void modificacionDeUsuarioTest () {
		var rol = new Rol (UsuarioEnum.ESTUDIANTE.name());
		var usuario = new Usuario ("UNRN-123456", "Jose Martin", "Momoa", rol);
		
		usuario.cambiarNombreCompleto("Jose Martin", "Martinez");
		
		assertEquals ("Martinez, Jose Martin", usuario.obtenerNombreCompeto());
	}
	public void recuperarUsuarioTest () {
		
	}
	@Test
	public void eliminarUsuarioTest () {
		
	}
}
