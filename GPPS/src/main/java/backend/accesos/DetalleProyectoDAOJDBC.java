package backend.accesos;

import entities.DetalleProyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleProyectoDAOJDBC implements DetalleProyectoDAO {

    public static final String LABEL_ERROR_DETALLE_PROYECTO = "Error al crear el detalle del proyecto.";
    public static final String LABEL_ERROR_UPDATE = "Error al actualizar el detalle del proyecto";
    public static final String LABEL_ERROR_DELETE = "No se encontró el detalle del proyecto para eliminar.";
    public static final String LAABEL_ERROR_FIND_DETALLE = "Error al buscar el detalle del proyecto: ";
    public static final String LABEL_ERROR_FIND_ALL_ENTIDAD = "Error al buscar detalles del proyecto por nombre de entidad: ";
    public static final String LABEL_ERROR_FIND_ALL_DETALLES = "Error al buscar todos los detalles del proyecto: ";

    public static final String QUERY_CREATE = "INSERT INTO DetalleProyecto (titulo, descripcion, areas_interes, " +
            "objetivos, aptitudes, id_proyecto, nombre_entidad) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_UPDATE = "UPDATE DetalleProyecto SET " +
            "titulo = ?, " +
            "descripcion = ?, " +
            "areasInteres = ?, " +
            "objetivos = ? " +
            "aptitudes = ?, " +
            "WHERE id_proyecto = ? AND nombre_entidad = ?";
    public static final String QUERY_DELETE = "DELETE FROM Detalle WHERE titulo = ? AND id_proyecto = ? AND nombre_entidad = ?";
    public static final String QUERY_FIND_DETALLE = "SELECT titulo, descripcion, areas_interes, objetivos, aptitudes " +
            "FROM DetalleProyecto " +
            "WHERE titulo = ? AND id_proyecto = ? AND nombre_entidad = ?";
    public static final String QUERY_FIND_ALL_DETALLE_ENTIDAD = "SELECT titulo, descripcion, areas_Interes, objetivos, aptitudes " +
            "FROM DetalleProyecto " +
            "WHERE nombre_entidad = ?";
    public static final String QUERY_FIND_ALL_DETALLES = "SELECT titulo, descripcion, areas_interes, objetivos, aptitudes " +
            "FROM DetalleProyecto";


    @Override
    public void create(DetalleProyecto detalleProyecto, int idProyecto, String nombreEntidad) {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement statement = conn.prepareStatement(QUERY_CREATE);
            statement.setString(1, detalleProyecto.obtenerTituloProyecto());
            statement.setString(2, detalleProyecto.obtenerDescripcionProyecto());
            statement.setString(3, detalleProyecto.obtenerAreasInteres());
            statement.setString(4, detalleProyecto.obtenerObjetivosProyecto());
            statement.setString(5, detalleProyecto.obtenerAptitudes());
            statement.setInt(6, idProyecto);
            statement.setString(7, nombreEntidad);

            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_DETALLE_PROYECTO);
            }

        } catch (Exception e) {
            throw new RuntimeException(LABEL_ERROR_DETALLE_PROYECTO + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void update(DetalleProyecto detalleProyecto, int idProyecto, String nombreEntidad) {
        try {
            Connection conn = ConnectionManager.getConnection();

            // Preparar la sentencia UPDATE para la tabla Detalle
            PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);

            statement.setString(1, detalleProyecto.obtenerAptitudes());
            statement.setString(2, detalleProyecto.obtenerTituloProyecto());
            statement.setString(3, detalleProyecto.obtenerDescripcionProyecto());
            statement.setString(4, detalleProyecto.obtenerAreasInteres());
            statement.setString(5, detalleProyecto.obtenerObjetivosProyecto());
            statement.setInt(6, idProyecto);
            statement.setString(7, nombreEntidad);

            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_UPDATE);
            }

        } catch (Exception e) {
            throw new RuntimeException(LABEL_ERROR_UPDATE + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void remove(String tituloProyecto, int idProyecto, String nombreEntidad) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement(QUERY_DELETE);
            statement.setString(1, tituloProyecto);
            statement.setInt(2, idProyecto);
            statement.setString(3, nombreEntidad);
            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_DELETE);
            }
        } catch (Exception e) {
            throw new RuntimeException(LABEL_ERROR_DELETE + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public DetalleProyecto find(String tituloProyecto, int idProyecto, String nombreEntidad) {
        DetalleProyecto detalleEncontrado = null;
        Connection conn;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(QUERY_FIND_DETALLE);
            statement.setString(1, tituloProyecto);
            statement.setInt(2, idProyecto);
            statement.setString(3, nombreEntidad);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                detalleEncontrado = new DetalleProyecto(
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("areas_interes"),
                        resultSet.getString("objetivos"),
                        resultSet.getString("aptitudes")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(LAABEL_ERROR_FIND_DETALLE + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }

        return detalleEncontrado;
    }

    @Override
    public List<DetalleProyecto> findAll(String nombreEntidad) {
        List<DetalleProyecto> detallesEncontrados = new ArrayList<>();
        Connection conn;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(QUERY_FIND_ALL_DETALLE_ENTIDAD);
            statement.setString(1, nombreEntidad);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleProyecto detalle = new DetalleProyecto(
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("areasInteres"),
                        resultSet.getString("objetivos"),
                        resultSet.getString("aptitudes")
                );
                detallesEncontrados.add(detalle);
            }

        } catch (Exception e) {
            throw new RuntimeException(LABEL_ERROR_FIND_ALL_ENTIDAD + e.getMessage());
        } finally {

            ConnectionManager.disconnect();
        }

        return detallesEncontrados;
    }

    @Override
    public List<DetalleProyecto> findAll() {
        List<DetalleProyecto> detallesEncontrados = new ArrayList<>();
        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = QUERY_FIND_ALL_DETALLES;
            statement = conn.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleProyecto detalle = new DetalleProyecto(
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("areas_interes"),
                        resultSet.getString("objetivos"),
                        resultSet.getString("aptitudes")
                );
                detallesEncontrados.add(detalle);
            }

        } catch (Exception e) {
            throw new RuntimeException(LABEL_ERROR_FIND_ALL_DETALLES + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
        return detallesEncontrados;
    }


}
