package citas.rest;

import citas.converter.MedicoConverter;
import citas.dto.MedicoDto;
import citas.entity.Medicos;
import citas.service.MedicoService;
import citas.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Autowired
    private MedicoConverter converter;

    @GetMapping
    public ResponseEntity<List<MedicoDto>> findAll() {
        List<MedicoDto> registros = service.findAll().stream()
                .map(converter::fromEntity)
                .collect(Collectors.toList());
        return new WrapperResponse(true, "Médicos encontrados", registros).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicoDto> create(@RequestBody Medicos medico) {
        MedicoDto registro = converter.fromEntity(service.save(medico));
        return new WrapperResponse(true, "Médico creado exitosamente", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> update(@PathVariable("id") int id, @RequestBody Medicos medico) {
        medico.setIdMedico(id); // Asegurar que el ID sea asignado al objeto
        MedicoDto registro = converter.fromEntity(service.save(medico));
        return new WrapperResponse(true, "Médico actualizado exitosamente", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> findById(@PathVariable("id") int id) {
        MedicoDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "Médico encontrado", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
            return new WrapperResponse(true, "Médico eliminado exitosamente", null).createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.BAD_REQUEST);
        }
    }
}
