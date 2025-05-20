import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonaTest {
    @Test
    public void testObjetoValido() {
        assertThrows(RuntimeException.class, () -> new FakeHeredaDePersona("Fake", "fake@correo.com", "123", "", "Fake", "1"));
        assertThrows(RuntimeException.class, () -> new FakeHeredaDePersona("Fake", "fake@correo.com", "123", "Fake", "", "1"));
        assertThrows(RuntimeException.class, () -> new FakeHeredaDePersona("Fake", "fake@correo.com", "123", "Fake", "Fake", ""));
    }
}
