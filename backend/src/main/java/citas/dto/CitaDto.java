package citas.dto;

import citas.entity.Medicos;
import citas.entity.Pacientes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaDto {
    private int idCita;
    private Pacientes Paciente;
    private Medicos Medico;
    private LocalDate fechaCita; // Cambiado a LocalDate
    private LocalTime horaCita; // Cambiado a LocalTime
    private String descripcion;
}
