package citas.rest;

import citas.converter.UsuarioConverter;
import citas.dto.UsuarioDto;
import citas.entity.Usuarios;
import citas.service.UsuarioService;
import citas.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioConverter converter;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<UsuarioDto> registros = service.findAll().stream()
                .map(converter::fromEntity)
                .collect(Collectors.toList());
        return new WrapperResponse(true, "Usuarios encontrados", registros).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody Usuarios usuario) {
        UsuarioDto registro = converter.fromEntity(service.save(usuario));
        return new WrapperResponse(true, "Usuario creado exitosamente", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable("id") int id, @RequestBody Usuarios usuario) {
        usuario.setIdUsuario(id); // Asegurarse de asignar el ID antes de guardar
        UsuarioDto registro = converter.fromEntity(service.save(usuario));
        return new WrapperResponse(true, "Usuario actualizado exitosamente", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable("id") int id) {
        UsuarioDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "Usuario encontrado", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioDto> findByUsername(@PathVariable("username") String username) {
        UsuarioDto registro = converter.fromEntity(service.findByUsername(username));
        return new WrapperResponse(true, "Usuario encontrado por username", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
            return new WrapperResponse(true, "Usuario eliminado exitosamente", null).createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.BAD_REQUEST);
        }
    }
}
