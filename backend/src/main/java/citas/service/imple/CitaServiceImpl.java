package citas.service.imple;

import citas.entity.Citas;
import citas.exception.GeneralException;
import citas.exception.NoDataFoundException;
import citas.exception.ValidateException;
import citas.repository.CitaRepository;
import citas.service.CitaService;
import citas.validator.CitaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Citas> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de citas");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citas> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de citas");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Citas findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe una cita con ese ID"));
        } catch (Exception e) {
            throw new GeneralException("Error al buscar cita por ID");
        }
    }

    @Override
    @Transactional
    public Citas save(Citas cita) {
        try {
            CitaValidator.save(cita);

            // Editar registro
            if (cita.getIdCita() != 0) {
                Citas existing = repository.findById(cita.getIdCita())
                        .orElseThrow(() -> new NoDataFoundException("No existe una cita con ese ID"));
                existing.setPaciente(cita.getPaciente());
                existing.setMedico(cita.getMedico());
                existing.setFechaCita(cita.getFechaCita());
                existing.setHoraCita(cita.getHoraCita());
                existing.setDescripcion(cita.getDescripcion());
                return repository.save(existing);
            }

            // Nuevo registro
            return repository.save(cita);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralException("Error al guardar la cita");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Citas cita = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe una cita con ese ID"));
            repository.delete(cita);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar la cita");
        }
    }
}
