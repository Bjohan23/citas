package citas.rest;

import citas.converter.PacienteConverter;
import citas.dto.PacienteDto;
import citas.entity.Pacientes;
import citas.service.PacienteService;
import citas.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @Autowired
    private PacienteConverter converter;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> findAll() {
        List<PacienteDto> registros = service.findAll().stream()
                .map(converter::fromEntity)
                .collect(Collectors.toList());
        return new WrapperResponse(true, "Pacientes encontrados", registros).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> create(@RequestBody Pacientes paciente) {
        PacienteDto registro = converter.fromEntity(service.save(paciente));
        return new WrapperResponse(true, "Paciente creado exitosamente", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> update(@PathVariable("id") int id, @RequestBody Pacientes paciente) {
        paciente.setIdPaciente(id); // Asegurar que el ID sea asignado al objeto
        PacienteDto registro = converter.fromEntity(service.save(paciente));
        return new WrapperResponse(true, "Paciente actualizado exitosamente", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> findById(@PathVariable("id") int id) {
        PacienteDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "Paciente encontrado", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
            return new WrapperResponse(true, "Paciente eliminado exitosamente", null).createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.BAD_REQUEST);
        }
    }
}
