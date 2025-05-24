package Persistencia;

import Modelo.DetalleProyecto;
import Modelo.Entidad;
import Modelo.EstadoProyectoEnum;
import Modelo.PlanDeTrabajo;
import Modelo.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProyectoDAOJDBC implements ProyectoDAO {

    public static final String LABEL_ERROR_CREAR_PROYECTO = "Error al crear el proyecto";
    public static final String LABEL_ERROR_UPDATE_PROYECTO = "Error al actualizar el proyecto";
    public static final String LABEL_ERROR_DELETE = "El proyecto no pudo ser eliminado";
    public static final String LABEL_ERROR_FIND_ENTIDAD = "Error: Entidad asociada no encontrada para nombre de usuario: ";
    public static final String LABEL_ERROR_FIND_ALL_PROYECTOS_ENTIDAD = "Error al buscar los proyectos de la entidad ";
    public static final String LABEL_ERROR_FIND_ALL_PROYECTOS_ESTADO = "Error al buscar los proyectos por estado";
    public static final String LABEL_ERROR_FIND_ALL_PROYECTOS = "Error al buscar todos los proyectos";

    public static final String query_create = "INSERT INTO proyecto(nombre_usuario_entidad, titulo, descripcion, areas_de_interes, objetivos, aptitudes, aprobado_por_director) VALUES (?, ?, ?, ?, ?, ?,?)";

    public static final String query_update = "UPDATE proyecto SET titulo = ?, descripcion = ?, " +
            "areas_de_interes = ?, objetivos = ?, aptitudes = ?, aprobado_por_director = ? " +
            "WHERE titulo = ? AND nombre_usuario_entidad = ?";

    public static final String query_remove = "DELETE FROM proyecto WHERE titulo = ? AND nombre_usuario_entidad = ?";

    public static final String query_find = "SELECT p.nombre_usuario_entidad, p.titulo, p.descripcion, p.areas_de_interes, " +
            "p.objetivos, p.aptitudes, p.aprobado_por_director, e.nombre, u.correo " +
            "FROM proyecto p " +
            "JOIN entidad e ON p.nombre_usuario_entidad = e.nombre_usuario " +
            "JOIN usuario u ON e.nombre_usuario = u.nombre_usuario " +
            "WHERE p.titulo = ? AND p.nombre_usuario_entidad = ?";

    public static final String query_findAllPorEntidad =  "SELECT p.nombre_usuario_entidad, p.titulo, p.descripcion, p.areas_de_interes, " +
            "p.objetivos, p.aptitudes, p.aprobado_por_director, e.nombre, u.correo " +
            "FROM proyecto p " +
            "JOIN entidad e ON p.nombre_usuario_entidad = e.nombre_usuario " +
            "JOIN usuario u ON e.nombre_usuario = u.nombre_usuario " +
            "WHERE p.nombre_usuario_entidad = ?";

    public static final String query_findAllPorEstado =  "SELECT p.nombre_usuario_entidad, p.titulo, p.descripcion, p.areas_de_interes, " +
            "p.objetivos, p.aptitudes, p.aprobado_por_director, e.nombre, u.correo " +
            "FROM proyecto p " +
            "JOIN entidad e ON p.nombre_usuario_entidad = e.nombre_usuario " +
            "JOIN usuario u ON e.nombre_usuario = u.nombre_usuario " +
            "WHERE p.aprobado_por_director = ?";

    public static final String query_findAll_all = "SELECT p.nombre_usuario_entidad, p.titulo, p.descripcion, p.areas_de_interes, " +
            "p.objetivos, p.aptitudes, p.aprobado_por_director, e.nombre, u.correo " +
            "FROM proyecto p " +
            "JOIN entidad e ON p.nombre_usuario_entidad = e.nombre_usuario " +
            "JOIN usuario u ON e.nombre_usuario = u.nombre_usuario";

    @Override
    public void create(Proyecto proyecto) {
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_create);

            statement.setString(1, proyecto.obtenerNombreEntidad());
            statement.setString(2, proyecto.obtenerTituloProyecto());
            statement.setString(3, proyecto.obtenerDescripcionProyecto());
            statement.setString(4, proyecto.obtenerAreasInteres());
            statement.setString(5, proyecto.obtenerObjetivosProyecto());
            statement.setString(6, proyecto.obtenerAptitudes());
            statement.setString(7, proyecto.obtenerEstado());

            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_CREAR_PROYECTO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void update(Proyecto proyecto, String tituloProyecto, String nombreEntidad) {
        Connection conn;
        PreparedStatement statement;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_update);


            statement.setString(1, proyecto.obtenerTituloProyecto());
            statement.setString(2, proyecto.obtenerDescripcionProyecto());
            statement.setString(3, proyecto.obtenerAreasInteres());
            statement.setString(4, proyecto.obtenerObjetivosProyecto());
            statement.setString(5, proyecto.obtenerAptitudes());
            statement.setString(6, proyecto.obtenerEstado());

            statement.setString(7, tituloProyecto);
            statement.setString(8, nombreEntidad);

            int cantidad = statement.executeUpdate();
            if (cantidad == 0) {
                throw new RuntimeException(LABEL_ERROR_UPDATE_PROYECTO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    @Override
    public void remove(String tituloProyecto, String nombreEntidad) {
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_remove);

            statement.setString(1, tituloProyecto);
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

    @Override
    public Optional<Proyecto> find(String tituloProyecto, String nombreEntidad) {
        Proyecto proyectoEncontrado = null;
        Connection conn;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_find);
            statement.setString(1, tituloProyecto);
            statement.setString(2, nombreEntidad);

            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            if (resultSet.next()) {
                // Recopila todos los datos del ResultSet
                String nombre_usuario_entidad = resultSet.getString("nombre_usuario_entidad");
                String titulo_proyecto = resultSet.getString("titulo");
                String descripcion_proyecto = resultSet.getString("descripcion");
                String areas_interes = resultSet.getString("areas_de_interes");
                String objetivos = resultSet.getString("objetivos");
                String aptitudes = resultSet.getString("aptitudes");
                String estado_aprobacion_director = resultSet.getString("aprobado_por_director");


                // 1. DetalleProyecto
                DetalleProyecto detalleProyecto = new DetalleProyecto(
                        titulo_proyecto,
                        descripcion_proyecto,
                        areas_interes,
                        objetivos,
                        aptitudes
                );

                // 2. Entidad

                Entidad entidad = new Entidad (nombre_usuario_entidad, resultSet.getString("correo"),
                        resultSet.getString("nombre"));

                // 3. Objeto Proyecto usando el CONSTRUCTOR SOBRECARGADO
                proyectoEncontrado = new Proyecto(
                        detalleProyecto,
                        entidad,
                        EstadoProyectoEnum.valueOf(estado_aprobacion_director));
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionManager.disconnect(); // Desconexión de la base de datos
        }
        return Optional.of(proyectoEncontrado);
    }

    @Override
    public List<Proyecto> findAll(String nombreEntidad) {
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_findAllPorEntidad);
            statement.setString(1, nombreEntidad);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                    String nombre_entidad = resultSet.getString("nombre_usuario_entidad");
                    String titulo_entidad = resultSet.getString("titulo");
                    String descripcion_proyecto = resultSet.getString("descripcion");
                    String areas_interes = resultSet.getString("areas_de_interes");
                    String objetivos = resultSet.getString("objetivos");
                    String aptitudes = resultSet.getString("aptitudes");
                    String estado_aprobacion_director = resultSet.getString("aprobado_por_director");


                    // 1. DetalleProyecto
                    DetalleProyecto detalleProyecto = new DetalleProyecto(
                            titulo_entidad,
                            descripcion_proyecto,
                            areas_interes,
                            objetivos,
                            aptitudes
                    );

                // 2. Entidad

                Entidad entidad = new Entidad (nombre_entidad, resultSet.getString("correo"),
                        resultSet.getString("nombre"));
                if (entidad == null) {
                    throw new RuntimeException(LABEL_ERROR_FIND_ALL_PROYECTOS_ENTIDAD);
                }
                // 3. Objeto Proyecto usando el CONSTRUCTOR SOBRECARGADO
                Proyecto proyectoEncontrado = new Proyecto(
                        detalleProyecto,
                        entidad,
                        EstadoProyectoEnum.valueOf(estado_aprobacion_director));
                proyectos.add(proyectoEncontrado);
            }

    } catch (Exception e) {
            throw new RuntimeException(e.getMessage();
        } finally {
            ConnectionManager.disconnect();
        }
        return proyectos;
    }

    @Override
    public List<Proyecto> findAll(EstadoProyectoEnum estado) {
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query_findAllPorEstado);
            statement.setString(1, String.valueOf(estado));

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String nombre_entidad = resultSet.getString("nombre_usuario_entidad");
                String titulo_entidad = resultSet.getString("titulo");
                String descripcion_proyecto = resultSet.getString("descripcion");
                String areas_interes = resultSet.getString("areas_de_interes");
                String objetivos = resultSet.getString("objetivos");
                String aptitudes = resultSet.getString("aptitudes");
                String estado_aprobacion_director = resultSet.getString("aprobado_por_director");


                // 1. DetalleProyecto
                DetalleProyecto detalleProyecto = new DetalleProyecto(
                        titulo_entidad,
                        descripcion_proyecto,
                        areas_interes,
                        objetivos,
                        aptitudes
                );

                // 2. Entidad

                Entidad entidad = new Entidad (nombre_entidad, resultSet.getString("correo"),
                        resultSet.getString("nombre"));
                if (entidad == null) {
                    throw new RuntimeException(LABEL_ERROR_FIND_ALL_PROYECTOS_ESTADO);
                }
                // 3. Objeto Proyecto usando el CONSTRUCTOR SOBRECARGADO
                Proyecto proyectoEncontrado = new Proyecto(
                        detalleProyecto,
                        entidad,
                        EstadoProyectoEnum.valueOf(estado_aprobacion_director));
                proyectos.add(proyectoEncontrado);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage();
        } finally {
            ConnectionManager.disconnect();
        }
        return proyectos;
    }

    @Override
    public List<Proyecto> findAll() {
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionManager.getConnection();

            resultSet = statement.executeQuery(query_findAll_all);

            while (resultSet.next()) {

                String nombre_entidad = resultSet.getString("nombre_usuario_entidad");
                String titulo_entidad = resultSet.getString("titulo");
                String descripcion_proyecto = resultSet.getString("descripcion");
                String areas_interes = resultSet.getString("areas_de_interes");
                String objetivos = resultSet.getString("objetivos");
                String aptitudes = resultSet.getString("aptitudes");
                String estado_aprobacion_director = resultSet.getString("aprobado_por_director");


                // 1. DetalleProyecto
                DetalleProyecto detalleProyecto = new DetalleProyecto(
                        titulo_entidad,
                        descripcion_proyecto,
                        areas_interes,
                        objetivos,
                        aptitudes
                );
                // 2. Entidad

                Entidad entidad = new Entidad (nombre_entidad, resultSet.getString("correo"),
                        resultSet.getString("nombre"));
                if (entidad == null) {
                    throw new RuntimeException(LABEL_ERROR_FIND_ALL_PROYECTOS_ESTADO);
                }
                // 3. Objeto Proyecto usando el CONSTRUCTOR SOBRECARGADO
                Proyecto proyectoEncontrado = new Proyecto(
                        detalleProyecto,
                        entidad,
                        EstadoProyectoEnum.valueOf(estado_aprobacion_director));
                proyectos.add(proyectoEncontrado);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage();
        } finally {
            ConnectionManager.disconnect();
        }
        return proyectos;

    }
}






