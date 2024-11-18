package citas.converter;

import citas.dto.CitaDto;
import citas.entity.Citas;
import org.springframework.stereotype.Component;

@Component
public class CitaConverter {

    public CitaDto fromEntity(Citas entity) {
        if (entity == null) return null;
        return CitaDto.builder()
                .idCita(entity.getIdCita())
                .Paciente(entity.getPaciente())
                .Medico(entity.getMedico())
                .fechaCita(entity.getFechaCita()) // Directamente LocalDate
                .horaCita(entity.getHoraCita()) // Directamente LocalTime
                .descripcion(entity.getDescripcion())
                .build();
    }

    public Citas fromDTO(CitaDto dto) {
        if (dto == null) return null;
        return Citas.builder()
                .idCita(dto.getIdCita())
                .paciente(dto.getPaciente())
                .medico(dto.getMedico())
                .fechaCita(dto.getFechaCita()) // Directamente LocalDate
                .horaCita(dto.getHoraCita()) // Directamente LocalTime
                .descripcion(dto.getDescripcion())
                .build();
    }
}
