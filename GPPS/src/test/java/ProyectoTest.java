import Modelo.DetalleProyecto;
import Modelo.Entidad;
import Modelo.PlanDeTrabajo;
import Modelo.Proyecto;
import org.junit.jupiter.api.Test;

import java.util.List;


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
        List<PlanDeTrabajo> planes = List.of(plan1, plan2);
        Entidad usuarioEntidad = new Entidad("perezSA", "perez@perez@gmail.com", "1234", "Perez Sociedad Anonima");
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
        List<PlanDeTrabajo> planes = List.of(plan1, plan2);
        Entidad usuarioEntidad = new Entidad("perezSA", "perez@perez@gmail.com", "1234", "Perez Sociedad Anonima");
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
        List<PlanDeTrabajo> planes = List.of(plan1, plan2);
        Entidad usuarioEntidad = null;
        Exception excepcion = assertThrows(RuntimeException.class, () -> new Proyecto(detalleProyecto, usuarioEntidad, planes));
        assertEquals(Proyecto.DESCRIPCION_ENTIDAD, excepcion.getMessage());
    }


}
