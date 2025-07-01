package mantenimiento.Cibertec.Proyecto.DTO;

import lombok.Data;

@Data
public class RegistrarMantenimientoDTO {
    private String descripcion;
    private int equipoId;
    private int tecnicoId;
    private String tipo_mantenimiento;
    private String estado;
    private String fecha;
}
