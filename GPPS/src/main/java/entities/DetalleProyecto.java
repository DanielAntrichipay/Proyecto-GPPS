package entities;

public class DetalleProyecto {
    public static final String DESCRIPCION_TITULO_PROYECTO = "El título del proyecto no puede estar vacío";
    public static final String DESCRIPCION_PROYECTO = "La descripción del proyecto no puede estar vacía";
    public static final String DESCRIPCION_AREAS_INTERES = "Las áreas de interés no pueden estar vacías";
    public static final String DESCRIPCION_OBJETIVOS_PROYECTO = "Los objetivos del proyecto no pueden estar vacíos";
    public static final String DESCRIPCION_APTITUDES = "Las aptitudes no pueden estar vacías";
    private final String aptitudes;
    private String tituloProyecto;
    private String descripcionProyecto;
    private String areasInteres;
    private String objetivosProyecto;

    public DetalleProyecto(String tituloProyecto, String descripcionProyecto, String areasInteres,
                           String objetivosProyecto, String aptitudes) {

        assertTituloProyecto(tituloProyecto);
        assertDescripcionProyecto(descripcionProyecto);
        assertAreasInteres(areasInteres);
        assertObjetivosProyecto(objetivosProyecto);
        assertAptitudes(aptitudes);

        this.tituloProyecto = tituloProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.areasInteres = areasInteres;
        this.objetivosProyecto = objetivosProyecto;
        this.aptitudes = aptitudes;
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

    private static void assertAreasInteres(String areasInteres) {
        if (areasInteres == null || areasInteres.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_AREAS_INTERES);
        }
    }

    private static void assertObjetivosProyecto(String objetivosProyecto) {
        if (objetivosProyecto == null || objetivosProyecto.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_OBJETIVOS_PROYECTO);
        }
    }

    private static void assertAptitudes(String aptitudes) {
        if (aptitudes == null || aptitudes.isEmpty()) {
            throw new RuntimeException(DESCRIPCION_APTITUDES);
        }
    }
}












