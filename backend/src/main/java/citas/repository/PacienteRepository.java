package citas.repository;

import citas.entity.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Pacientes, Integer> {
    List<Pacientes> findByNombreContaining(String nombre);
}
