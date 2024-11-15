package citas.service.imple;

import citas.entity.Pacientes;
import citas.exception.GeneralException;
import citas.exception.NoDataFoundException;
import citas.exception.ValidateException;
import citas.repository.PacienteRepository;
import citas.service.PacienteService;
import citas.validator.PacienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pacientes> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de pacientes");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pacientes> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de pacientes");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pacientes findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un paciente con ese ID"));
        } catch (Exception e) {
            throw new GeneralException("Error al buscar paciente por ID");
        }
    }

    @Override
    @Transactional
    public Pacientes save(Pacientes paciente) {
        try {
            PacienteValidator.save(paciente);

            // Editar registro
            if (paciente.getIdPaciente() != 0) {
                Pacientes existing = repository.findById(paciente.getIdPaciente())
                        .orElseThrow(() -> new NoDataFoundException("No existe un paciente con ese ID"));
                existing.setNombre(paciente.getNombre());
                existing.setTelefono(paciente.getTelefono());
                existing.setEmail(paciente.getEmail());
                return repository.save(existing);
            }

            // Nuevo registro
            return repository.save(paciente);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralException("Error al guardar el paciente");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Pacientes paciente = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un paciente con ese ID"));
            repository.delete(paciente);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar el paciente");
        }
    }
}
