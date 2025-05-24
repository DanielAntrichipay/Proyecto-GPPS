package Persistencia;

import Modelo.EstadoProyectoEnum;
import Modelo.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoDAO {

    void create(Proyecto proyecto);

    void update(Proyecto proyecto, String tituloProyecto, String nombreEntidad);

    void remove(String tituloProyecto, String nombreEntidad);

   Optional<Proyecto> find(String tituloProyecto, String nombreEntidad);

    List<Proyecto> findAll(String nombreEntidad);

    List<Proyecto> findAll(EstadoProyectoEnum estado);

    List<Proyecto> findAll();

}
