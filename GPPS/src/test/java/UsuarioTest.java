import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {
    @Test
    public void testObjetoValido() {
        assertThrows(RuntimeException.class, () -> new FakeHeredaDeUsario("", "fake@correo.com", "123"));
        assertThrows(RuntimeException.class, () -> new FakeHeredaDeUsario("hijoFake", "", "123"));
        assertThrows(RuntimeException.class, () -> new FakeHeredaDeUsario("hijoFake", "fake@correo.com", ""));
    }
}
