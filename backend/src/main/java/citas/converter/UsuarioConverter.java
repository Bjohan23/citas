package citas.converter;

import citas.dto.UsuarioDto;
import citas.entity.Usuarios;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioDto fromEntity(Usuarios entity) {
        if (entity == null) return null;
        return UsuarioDto.builder()
                .idUsuario(entity.getIdUsuario())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public Usuarios fromDTO(UsuarioDto dto) {
        if (dto == null) return null;
        return Usuarios.builder()
                .idUsuario(dto.getIdUsuario())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
