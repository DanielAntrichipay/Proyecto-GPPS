package ar.unrn.entities;

import java.util.HashSet;
import java.util.Set;

public class Proyecto {
    static final String DESCRIPCION_DETALLE_PROYECTO = "El detalle del proyecto no puede ser nulo";
    static final String DESCRIPCION_CANTIDAD_ALUMNOS = "La cantidad de alumnos no puede ser menor o igual a cero";
    static final String DESCRIPCION_DIRECCION = "La dirección no puede ser nula o vacía";
    static final String DESCRIPCION_CIUDAD = "La ciudad no puede ser nula o vacía";
    static final String DESCRIPCION_USUARIO_CREADOR = "El creador del proyecto no puede estar vacio";
    static final String DESCRIPCION_ENTIDAD = "La entidad no puede ser nula";

    private DetalleProyecto detalleProyecto;
    private Usuario usuarioCreador;
    private Usuario usuarioEntidad;
    private Usuario usuarioAlumno;
    private String estado;
    private int cantAlumnos;
    private String direccion;
    private String ciudad;
    private Set<PlanDeTrabajo> planesDeTrabajo;


    private Proyecto(DetalleProyecto detallePoyecto, Usuario usuarioCreador, Usuario usuarioEntidad,
                     Usuario usuarioAlumno, String estado, int cantAlumnos, String direccion, String ciudad) {

        this.detalleProyecto = detalleProyecto;
        this.estado = estado;
        this.cantAlumnos = cantAlumnos;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.usuarioCreador= usuarioCreador;
        this.usuarioAlumno = usuarioAlumno;
        this.usuarioEntidad = usuarioEntidad;
        this.planesDeTrabajo = new HashSet<>();
    }

    public static Proyecto crearProyecto(DetalleProyecto detalleProyecto, Usuario usuarioCreador, Usuario usuarioEntidad,
                                         Usuario usuarioAlumno, int cantAlumnos,
                                         String estado, String direccion, String ciudad) {

        assertDetalleProyecto(detalleProyecto);
        assertCantidadAlumnos(cantAlumnos);
        assertDireccion(direccion);
        assertCiudad(ciudad);
        assertUsuarioCreador(usuarioCreador);
        assertUsuarioEntidad(usuarioEntidad);

        return new Proyecto(detalleProyecto, usuarioCreador, usuarioEntidad, usuarioAlumno, estado, cantAlumnos, direccion, ciudad);
    }

    private static void assertDetalleProyecto(DetalleProyecto detalleProyecto) {
        if (detalleProyecto == null) {
            throw new RuntimeException(DESCRIPCION_DETALLE_PROYECTO);
        }
    }

    private static void assertCantidadAlumnos(int cantAlumnos) {
        if (cantAlumnos <= 0) {
            throw new RuntimeException(DESCRIPCION_CANTIDAD_ALUMNOS);
        }
    }

    private static void assertDireccion(String direccion) {
        if (direccion == null || direccion.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_DIRECCION);
        }
    }

    private static void assertCiudad(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_CIUDAD);
        }
    }

    private static void assertUsuarioCreador (Usuario usuarioCreador) {
        if (usuarioCreador == null) {
            throw  new RuntimeException(DESCRIPCION_USUARIO_CREADOR);
        }
    }

    private static void  assertUsuarioEntidad(Usuario usuarioEntidad) {
        if (usuarioEntidad == null) {
            throw new RuntimeException (DESCRIPCION_ENTIDAD);

        }
    }

    void agregarPlanDeTrabajo(PlanDeTrabajo planDeTrabajo) {
        this.planesDeTrabajo.add(planDeTrabajo);
    }

}
