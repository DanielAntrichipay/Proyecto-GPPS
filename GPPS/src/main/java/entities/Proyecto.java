package entities;

import commons.EstadoProyectoEnum;

import java.util.Set;

public class Proyecto {
    public static final String DESCRIPCION_DETALLE_PROYECTO = "El detalle del proyecto no puede ser nulo";
    public static final String DESCRIPCION_ENTIDAD = "La entidad no puede ser nula";

    private DetalleProyecto detalleProyecto;
    private Entidad usuarioEntidad;
    private EstadoProyectoEnum estado;
    private Set<PlanDeTrabajo> planesDeTrabajo;


    public Proyecto(DetalleProyecto detalleProyecto, Entidad usuarioEntidad, Set<PlanDeTrabajo> planesDeTrabajo) {
        assertDetalleProyecto(detalleProyecto);
        assertUsuarioEntidad(usuarioEntidad);

        this.detalleProyecto = detalleProyecto;
        this.estado = EstadoProyectoEnum.PENDIENTE;

        this.usuarioEntidad = usuarioEntidad;
        this.planesDeTrabajo = planesDeTrabajo;
    }

    private static void assertDetalleProyecto(DetalleProyecto detalleProyecto) {
        if (detalleProyecto == null) {
            throw new RuntimeException(DESCRIPCION_DETALLE_PROYECTO);
        }
    }

    private static void assertUsuarioEntidad(Entidad usuarioEntidad) {
        if (usuarioEntidad == null) {
            throw new RuntimeException(DESCRIPCION_ENTIDAD);

        }
    }

    public int cantidadPlanesTrabajo() {
        return this.planesDeTrabajo.size();
    }

    public void aprobarProyecto() {
        this.estado = EstadoProyectoEnum.APROBADO;
    }

    public void rechazarProyecto() {
        this.estado = EstadoProyectoEnum.RECHAZADO;
    }

    //todo definir si vamos a tener este método
    public String obtenerEstado() {
        return String.valueOf(this.estado);
    }

    public String obtenerNombreEntidad() {
        return this.usuarioEntidad.obtenerNombreCompeto();
    }
}
