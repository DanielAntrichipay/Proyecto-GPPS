package backend.accesos;

import commons.EstadoProyectoEnum;
import entities.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProyectoDAOJDBC implements ProyectoDAO {

    public static final String LABEL_ERROR_CREAR_PROYECTO = "Error al crear el proyecto";
    public static final String LABEL_ERROR_UPDATE_PROYECTO = "Error al actualizar el proyecto";
    public static final String LABEL_ERROR_DELETE = "El proyecto no pudo ser eliminado";
    public static final String query_create = "INSERT INTO proyectos(estado_proyecto, nombre_entidad) VALUES (?,?)";
    public static final String query_update = "UPDATE proyectos SET estado_proyecto = ?, nombre_entidad = ? WHERE id_proyecto = ?";
    public static final String query_remove = "DELETE FROM proyectos WHERE id_proyecto = ? AND nombre_entidad = ?";

    @Override
    public void create(Proyecto proyecto) {
        try {
            Connection conn = ConnectionManager
                    .getConnection();

            PreparedStatement statement = conn
                    .prepareStatement(query_create);

            statement.setString(1, proyecto.obtenerEstado());
            statement.setString(2, proyecto.obtenerNombreEntidad());

            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_CREAR_PROYECTO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
    }


    @Override
    public void update(Proyecto proyecto, int idProyecto, String nombreEntidad) {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement statement = conn
                    .prepareStatement(query_update);

            statement.setString(1, proyecto.obtenerEstado());
            statement.setString(2, nombreEntidad);
            statement.setInt(3, idProyecto);


            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_UPDATE_PROYECTO + idProyecto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void remove(int idProyecto, String nombreEntidad) {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement statement = conn
                    .prepareStatement(query_remove);

            statement.setInt(1, idProyecto);
            statement.setString(2, nombreEntidad);

            int cantidad = statement.executeUpdate();

            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_DELETE);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    //TODO Preguntar que devuelvo en proyecto
    @Override
    public Proyecto find(int idProyecto, String nombreEntidad) {

        return proyectoEncontrado;
    }

    @Override
    public List<Proyecto> findAll(String nombreEntidad) {
        return List.of();
    }

    @Override
    public List<Proyecto> findAll(EstadoProyectoEnum estado) {
        return List.of();
    }

    @Override
    public List<Proyecto> findAll() {
        return List.of();
    }
}
