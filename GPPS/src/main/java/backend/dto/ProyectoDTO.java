package backend.dto;

import entities.DetalleProyecto;
import entities.PlanDeTrabajo;
import entities.Usuario;

import java.util.Set;

public record ProyectoDTO(DetalleProyecto detalleProyecto, Usuario usuarioEntidad, Set<PlanDeTrabajo> planesDeTrabajo) {
}
