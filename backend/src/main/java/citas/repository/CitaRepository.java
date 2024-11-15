package citas.repository;

import citas.entity.Citas;
import citas.entity.Medicos;
import citas.entity.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Citas, Integer> {
    List<Citas> findByPaciente(Pacientes paciente);
    List<Citas> findByMedico(Medicos medico);
    List<Citas> findByFechaCita(LocalDate fechaCita);
}