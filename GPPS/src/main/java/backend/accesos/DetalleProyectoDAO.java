package backend.accesos;

import entities.DetalleProyecto;

import java.util.List;

public interface DetalleProyectoDAO {

    void create(DetalleProyecto detalleProyecto, int idProyecto, String nombreEntidad);

    void update(DetalleProyecto detalleProyecto, int idProyecto, String nombreEntidad);

    void remove(String tituloProyecto, int idProyecto, String nombreEntidad);

    DetalleProyecto find(String tituloProyecto, int idProyecto, String nombreEntidad);

    List<DetalleProyecto> findAll(String nombreEntidad);

    List<DetalleProyecto> findAll();
}
