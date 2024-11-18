package citas.rest;

import citas.converter.CitaConverter;
import citas.dto.CitaDto;
import citas.entity.Citas;
import citas.service.CitaService;
import citas.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @Autowired
    private CitaConverter converter;

    @GetMapping
    public ResponseEntity<List<CitaDto>> findAll() {
        List<CitaDto> registros = service.findAll().stream()
                .map(converter::fromEntity)
                .collect(Collectors.toList());
        return new WrapperResponse(true, "Citas encontradas", registros).createResponse(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CitaDto> create(@RequestBody Citas cita) {
        CitaDto registro = converter.fromEntity(service.save(cita));
        return new WrapperResponse(true, "Cita creada exitosamente", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> update(@PathVariable("id") int id, @RequestBody Citas cita) {
        cita.setIdCita(id); // Asegurar que el ID sea asignado al objeto
        CitaDto registro = converter.fromEntity(service.save(cita));
        return new WrapperResponse(true, "Cita actualizada exitosamente", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDto> findById(@PathVariable("id") int id) {
        CitaDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "Cita encontrada", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
            return new WrapperResponse(true, "Cita eliminada exitosamente", null).createResponse(HttpStatus.OK);
        } catch (Exception e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.BAD_REQUEST);
        }
    }
}
