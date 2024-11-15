package citas.converter;

import citas.dto.MedicoDto;
import citas.entity.Medicos;
import org.springframework.stereotype.Component;

@Component
public class MedicoConverter {

    public MedicoDto fromEntity(Medicos entity) {
        if (entity == null) return null;
        return MedicoDto.builder()
                .idMedico(entity.getIdMedico())
                .nombre(entity.getNombre())
                .especialidad(entity.getEspecialidad())
                .telefono(entity.getTelefono())
                .build();
    }

    public Medicos fromDTO(MedicoDto dto) {
        if (dto == null) return null;
        return Medicos.builder()
                .idMedico(dto.getIdMedico())
                .nombre(dto.getNombre())
                .especialidad(dto.getEspecialidad())
                .telefono(dto.getTelefono())
                .build();
    }
}
