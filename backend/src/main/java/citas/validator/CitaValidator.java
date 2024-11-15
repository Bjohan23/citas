package citas.validator;

import citas.entity.Citas;
import citas.exception.ValidateException;

public class CitaValidator {

    public static void save(Citas cita) {
        if (cita.getPaciente() == null) {
            throw new ValidateException("El paciente es requerido para la cita");
        }
        if (cita.getMedico() == null) {
            throw new ValidateException("El médico es requerido para la cita");
        }
        if (cita.getFechaCita() == null) {
            throw new ValidateException("La fecha de la cita es requerida");
        }
        if (cita.getHoraCita() == null) {
            throw new ValidateException("La hora de la cita es requerida");
        }
        if (cita.getDescripcion() != null && cita.getDescripcion().length() > 65535) {
            throw new ValidateException("La descripción de la cita no debe exceder los 65535 caracteres");
        }
    }
}
