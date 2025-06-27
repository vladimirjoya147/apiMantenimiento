package mantenimiento.Cibertec.Proyecto.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MantenimientoDTO {
    private LocalDate fecha;
    private String descripcion;
    private String nombreEquipo;
    private String nombreTecnico;
    private String tipoMantenimiento;
    private String estado;
}
