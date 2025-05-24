package Modelo;

import java.util.List;


public class Proyecto {
    public static final String DESCRIPCION_DETALLE_PROYECTO = "El detalle del proyecto no puede ser nulo";
    public static final String DESCRIPCION_ENTIDAD = "La entidad no puede ser nula";

    private DetalleProyecto detalleProyecto;
    private Entidad usuarioEntidad;
    private EstadoProyectoEnum estado;
    private List<PlanDeTrabajo> planesDeTrabajo;




    public Proyecto(DetalleProyecto detalleProyecto, Entidad usuarioEntidad, List<PlanDeTrabajo> planesDeTrabajo) {
        assertDetalleProyecto(detalleProyecto);
        assertUsuarioEntidad(usuarioEntidad);

        this.detalleProyecto = detalleProyecto;
        this.usuarioEntidad = usuarioEntidad;
        this.planesDeTrabajo = planesDeTrabajo;
    }

    public Proyecto(DetalleProyecto detalleProyecto, Entidad usuarioEntidad, EstadoProyectoEnum estado) {
        assertDetalleProyecto(detalleProyecto);
        assertUsuarioEntidad(usuarioEntidad);

        this.detalleProyecto = detalleProyecto;
        this.usuarioEntidad = usuarioEntidad;
        this.estado = estado;

    }

    public Proyecto(DetalleProyecto detalleProyecto, Entidad usuarioEntidad, List<PlanDeTrabajo> planesDeTrabajo, EstadoProyectoEnum estado) {
        assertDetalleProyecto(detalleProyecto);
        assertUsuarioEntidad(usuarioEntidad);

        this.detalleProyecto = detalleProyecto;
        this.estado = estado;

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

    public void pendienteProyecto() {this.estado = EstadoProyectoEnum.PENDIENTE;}


    public String obtenerEstado() {
        return String.valueOf(this.estado);
    }

    public String obtenerNombreEntidad() {
        return this.usuarioEntidad.getNombre();
    }


    public String obtenerTituloProyecto() {
        return detalleProyecto.obtenerTituloProyecto();
    }

    public String obtenerDescripcionProyecto() {
        return detalleProyecto.obtenerDescripcionProyecto();
    }

    public String obtenerAreasInteres() {
        return detalleProyecto.obtenerAreasInteres();
    }

    public String obtenerObjetivosProyecto() {
        return detalleProyecto.obtenerObjetivosProyecto();
    }

    public String obtenerAptitudes() {
        return detalleProyecto.obtenerAptitudes();
    }


}
