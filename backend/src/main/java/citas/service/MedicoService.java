package citas.service;

import citas.entity.Medicos;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicoService {
    public List<Medicos> findAll(Pageable page);
    public List<Medicos> findAll();
    public Medicos findById(int id);
    public Medicos save(Medicos medico);
    public void delete(int id);
}
