package citas.repository;

import citas.entity.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medicos, Integer> {
    List<Medicos> findByEspecialidadContaining(String especialidad);
}
