import entities.DetalleProyecto;
import entities.PlanDeTrabajo;
import entities.Proyecto;
import entities.Rol;
import entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProyectoTest {

    @Test
    public void crearProyecto() {
        String titulo = "Desarrollador backend";
        String descripcionProyecto = "Desarrollador backend para una aplicacion web";
        String areasInteres = "Desarrollo web, backend";
        String objetivosProyecto = "Desarrollar una aplicacion web";
        String aptitudes = "Java, Spring, Hibernate";
        DetalleProyecto detalleProyecto = new DetalleProyecto(titulo, descripcionProyecto, areasInteres, objetivosProyecto, aptitudes);
        PlanDeTrabajo plan1 = new PlanDeTrabajo();
        PlanDeTrabajo plan2 = new PlanDeTrabajo();
        Set<PlanDeTrabajo> planes = Set.of(plan1, plan2);
        Rol rolEntidad = new Rol("Entidad");
        Usuario usuarioEntidad = new Usuario("123456", "Juan", "Perez", rolEntidad);
        Proyecto p1 = new Proyecto(detalleProyecto, usuarioEntidad, planes);
        assertEquals(2, p1.cantidadPlanesTrabajo());
    }

    @Test
    public void crearDetalleProyectoTituloEnBlank() {
        String titulo = "";
        String descripcionProyecto = "Desarrollador backend para una aplicacion web";
        String areasInteres = "Desarrollo web, backend";
        String objetivosProyecto = "Desarrollar una aplicacion web";
        String aptitudes = "Java, Spring, Hibernate";
        Exception excepcion = assertThrows(RuntimeException.class, () -> new DetalleProyecto(titulo, descripcionProyecto, areasInteres, objetivosProyecto, aptitudes));
        assertEquals(DetalleProyecto.DESCRIPCION_TITULO_PROYECTO, excepcion.getMessage());
    }

    @Test
    public void crearDetalleProyectoAptitudesEnBlank() {
        String titulo = "Desarrollador backend";
        String descripcionProyecto = "Desarrollador backend para una aplicacion web";
        String areasInteres = "Desarrollo web, backend";
        String objetivosProyecto = "Desarrollar una aplicacion web";
        String aptitudes = "";
        Exception excepcion = assertThrows(RuntimeException.class, () -> new DetalleProyecto(titulo, descripcionProyecto, areasInteres, objetivosProyecto, aptitudes));
        assertEquals(DetalleProyecto.DESCRIPCION_APTITUDES, excepcion.getMessage());
    }

    @Test
    public void crearProyectoConDetalleNulo() {
        DetalleProyecto detalleProyecto = null;
        PlanDeTrabajo plan1 = new PlanDeTrabajo();
        PlanDeTrabajo plan2 = new PlanDeTrabajo();
        Set<PlanDeTrabajo> planes = Set.of(plan1, plan2);
        Rol rolEntidad = new Rol("Entidad");
        Usuario usuarioEntidad = new Usuario("123456", "Juan", "Perez", rolEntidad);
        Exception excepcion = assertThrows(RuntimeException.class, () -> new Proyecto(detalleProyecto, usuarioEntidad, planes));
        assertEquals(Proyecto.DESCRIPCION_DETALLE_PROYECTO, excepcion.getMessage());
    }

    @Test
    public void crearProyectoSinEntidad() {
        String titulo = "Desarrollador backend";
        String descripcionProyecto = "Desarrollador backend para una aplicacion web";
        String areasInteres = "Desarrollo web, backend";
        String objetivosProyecto = "Desarrollar una aplicacion web";
        String aptitudes = "Java, Spring, Hibernate";
        DetalleProyecto detalleProyecto = new DetalleProyecto(titulo, descripcionProyecto, areasInteres, objetivosProyecto, aptitudes);
        PlanDeTrabajo plan1 = new PlanDeTrabajo();
        PlanDeTrabajo plan2 = new PlanDeTrabajo();
        Set<PlanDeTrabajo> planes = Set.of(plan1, plan2);
        Rol rolEntidad = new Rol("Entidad");
        Usuario usuarioEntidad = null;
        Exception excepcion = assertThrows(RuntimeException.class, () -> new Proyecto(detalleProyecto, usuarioEntidad, planes));
        assertEquals(Proyecto.DESCRIPCION_ENTIDAD, excepcion.getMessage());
    }


}
