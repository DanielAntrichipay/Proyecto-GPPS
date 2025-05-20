package Modelo;

import java.util.Set;

public record ProyectoDTO(DetalleProyecto detalleProyecto, Usuario usuarioEntidad, Set<PlanDeTrabajo> planesDeTrabajo) {
}
