package citas.converter;

import citas.dto.PacienteDto;
import citas.entity.Pacientes;
import org.springframework.stereotype.Component;

@Component
public class PacienteConverter {

    public PacienteDto fromEntity(Pacientes entity) {
        if (entity == null) return null;
        return PacienteDto.builder()
                .idPaciente(entity.getIdPaciente())
                .nombre(entity.getNombre())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .build();
    }

    public Pacientes fromDTO(PacienteDto dto) {
        if (dto == null) return null;
        return Pacientes.builder()
                .idPaciente(dto.getIdPaciente())
                .nombre(dto.getNombre())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }
}
