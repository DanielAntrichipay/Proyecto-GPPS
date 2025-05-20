import Modelo.TutorExterno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TutorExternoTest {
    @Test
    protected void testObjetoValido() {
        assertThrows(RuntimeException.class, () -> new TutorExterno("nombre", "correo", "123", "nombre", "apellido", "123", ""));
    }
}
