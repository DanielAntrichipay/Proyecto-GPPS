package Modelo;

import java.util.List;

public record ProyectoDTO(DetalleProyectoDTO detalleProyecto, EntidadDTO usuarioEntidad, List<PlanDeTrabajoDTO> planesDeTrabajo) {
}
