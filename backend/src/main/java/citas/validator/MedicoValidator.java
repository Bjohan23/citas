package citas.validator;

import citas.entity.Medicos;
import citas.exception.ValidateException;

public class MedicoValidator {

    public static void save(Medicos medico) {
        if (medico.getNombre() == null || medico.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del médico es requerido");
        }
        if (medico.getNombre().length() > 100) {
            throw new ValidateException("El nombre del médico no debe exceder los 100 caracteres");
        }
        if (medico.getEspecialidad() != null && medico.getEspecialidad().length() > 50) {
            throw new ValidateException("La especialidad no debe exceder los 50 caracteres");
        }
        if (medico.getTelefono() != null && medico.getTelefono().length() > 15) {
            throw new ValidateException("El teléfono del médico no debe exceder los 15 caracteres");
        }
    }
}
