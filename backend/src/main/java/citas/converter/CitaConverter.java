package citas.converter;

import citas.dto.CitaDto;
import citas.entity.Citas;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

@Component
public class CitaConverter {

    public CitaDto fromEntity(Citas entity) {
        if (entity == null) return null;
        return CitaDto.builder()
                .idCita(entity.getIdCita())
                .idPaciente(entity.getPaciente().getIdPaciente())
                .idMedico(entity.getMedico().getIdMedico())
                .fechaCita(entity.getFechaCita().toString())
                .horaCita(entity.getHoraCita().toString())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public Citas fromDTO(CitaDto dto) {
        if (dto == null) return null;
        return Citas.builder()
                .idCita(dto.getIdCita())
                .fechaCita(Date.valueOf(dto.getFechaCita()).toLocalDate())
                .horaCita(Time.valueOf(dto.getHoraCita()).toLocalTime())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
