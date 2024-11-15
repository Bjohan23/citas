package citas.service;

import citas.entity.Pacientes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteService {
    public List<Pacientes> findAll(Pageable page);
    public List<Pacientes> findAll();
    public Pacientes findById(int id);
    public Pacientes save(Pacientes paciente);
    public void delete(int id);
}
