package citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaDto {
    private int idCita;
    private int idPaciente;
    private int idMedico;
    private String fechaCita;
    private String horaCita;
    private String descripcion;
}
