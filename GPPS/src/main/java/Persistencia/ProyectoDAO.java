package Persistencia;

import Modelo.EstadoProyectoEnum;
import Modelo.Proyecto;

import java.util.List;

public interface ProyectoDAO {

    void create(Proyecto proyecto);

    void update(Proyecto proyecto, int idProyecto, String nombreEntidad);

    void remove(int idProyecto, String nombreEntidad);

    Proyecto find(int idProyecto, String nombreEntidad);

    List<Proyecto> findAll(String nombreEntidad);

    List<Proyecto> findAll(EstadoProyectoEnum estado);

    List<Proyecto> findAll();

}
