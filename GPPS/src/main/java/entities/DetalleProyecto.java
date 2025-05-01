package ar.unrn.entities;

import java.util.Set;

public class DetalleProyecto {

    static final String DESCRIPCION_TITULO_PROYECTO = "El título del proyecto no puede estar vacío";
    static final String DESCRIPCION_PROYECTO = "La descripción del proyecto no puede estar vacía";
    static final String DESCRIPCION_AREAS_INTERES = "Las áreas de interés no pueden estar vacías";
    static final String DESCRIPCION_OBJETIVOS_PROYECTO = "Los objetivos del proyecto no pueden estar vacíos";
    static final String DESCRIPCION_APTITUDES = "Las aptitudes no pueden estar vacías";

    private String tituloProyecto;
    private String descripcionProyecto;
    private Set<String> areasInteres;
    private Set<String> objetivosProyecto;
    private final Set<String> aptitudes;



    private DetalleProyecto(String tituloProyecto, String descripcionProyecto, Set<String> areasInteres,
                           Set<String> objetivosProyecto, Set <String> aptitudes) {
        this.tituloProyecto = tituloProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.areasInteres = areasInteres;
        this.objetivosProyecto = objetivosProyecto;
        this.aptitudes = aptitudes;
    }

    static DetalleProyecto crearDetalleProyecto(String tituloProyecto, String descripcionProyecto, Set<String> areasInteres, Set<String> objetivosProyecto, Set<String> aptitudes) {
        assertTituloProyecto(tituloProyecto);
        assertDescripcionProyecto(descripcionProyecto);
        assertAreasInteres(areasInteres);
        assertObjetivosProyecto(objetivosProyecto);
        assertAptitudes(aptitudes);

        return new DetalleProyecto(tituloProyecto, descripcionProyecto, areasInteres, objetivosProyecto, aptitudes);
    }

    private static void assertTituloProyecto(String tituloProyecto) {
        if (tituloProyecto == null || tituloProyecto.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_TITULO_PROYECTO);
        }
    }

    private static void assertDescripcionProyecto(String descripcionProyecto) {
        if (descripcionProyecto == null || descripcionProyecto.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_PROYECTO);
        }
    }

    private static void assertAreasInteres(Set<String> areasInteres) {
        if (areasInteres == null || areasInteres.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_AREAS_INTERES);
        }
    }

    private static void assertObjetivosProyecto(Set<String> objetivosProyecto) {
        if (objetivosProyecto == null || objetivosProyecto.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_OBJETIVOS_PROYECTO);
        }
    }

    private static void assertAptitudes(Set<String> aptitudes) {
        if (aptitudes == null || aptitudes.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_APTITUDES);
        }
    }
}










