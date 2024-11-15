package citas.validator;

import citas.entity.Pacientes;
import citas.exception.ValidateException;

public class PacienteValidator {

    public static void save(Pacientes paciente) {
        if (paciente.getNombre() == null || paciente.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del paciente es requerido");
        }
        if (paciente.getNombre().length() > 100) {
            throw new ValidateException("El nombre del paciente no debe exceder los 100 caracteres");
        }
        if (paciente.getTelefono() != null && paciente.getTelefono().length() > 15) {
            throw new ValidateException("El teléfono del paciente no debe exceder los 15 caracteres");
        }
        if (paciente.getEmail() != null && paciente.getEmail().length() > 100) {
            throw new ValidateException("El email del paciente no debe exceder los 100 caracteres");
        }
    }
}
