package citas.service.imple;

import citas.entity.Medicos;
import citas.exception.GeneralException;
import citas.exception.NoDataFoundException;
import citas.exception.ValidateException;
import citas.repository.MedicoRepository;
import citas.service.MedicoService;
import citas.validator.MedicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Medicos> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de médicos");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicos> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de médicos");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Medicos findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un médico con ese ID"));
        } catch (Exception e) {
            throw new GeneralException("Error al buscar médico por ID");
        }
    }

    @Override
    @Transactional
    public Medicos save(Medicos medico) {
        try {
            MedicoValidator.save(medico);

            // Editar registro
            if (medico.getIdMedico() != 0) {
                Medicos existing = repository.findById(medico.getIdMedico())
                        .orElseThrow(() -> new NoDataFoundException("No existe un médico con ese ID"));
                existing.setNombre(medico.getNombre());
                existing.setTelefono(medico.getTelefono());
                existing.setEspecialidad(medico.getEspecialidad());
                return repository.save(existing);
            }

            // Nuevo registro
            return repository.save(medico);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralException("Error al guardar el médico");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Medicos medico = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un médico con ese ID"));
            repository.delete(medico);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar el médico");
        }
    }
}
