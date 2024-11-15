package citas.service;

import citas.entity.Citas;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CitaService {
    public List<Citas> findAll(Pageable page);
    public List<Citas> findAll();
    public Citas findById(int id);
    public Citas save(Citas cita);
    public void delete(int id);
}
