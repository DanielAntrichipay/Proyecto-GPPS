import entities.Estudiante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class EstudianteTest {
    @Test
    public void testObjetoValido() {
        assertThrows(RuntimeException.class, () ->
                new Estudiante("a", "blabla@bla.com", "123", "nombre", "apelldo", "53235252", "")
        );
    }
}
