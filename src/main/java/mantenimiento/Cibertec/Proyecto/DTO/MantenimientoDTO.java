package mantenimiento.Cibertec.Proyecto.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MantenimientoDTO {
    private int id;
    private String tipo_mantenimiento;
    private String descripcion;
    private String nombreEquipo;
    private String nombreTecnico;
    private String tipoMantenimiento;
    private String estado;
}
