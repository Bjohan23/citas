package citas.validator;

import citas.entity.Usuarios;
import citas.exception.ValidateException;

public class UsuarioValidator {

    public static void save(Usuarios usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new ValidateException("El nombre de usuario es requerido");
        }
        if (usuario.getUsername().length() > 50) {
            throw new ValidateException("El nombre de usuario no debe exceder los 50 caracteres");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new ValidateException("La contraseña es requerida");
        }
        if (usuario.getPassword().length() > 255) {
            throw new ValidateException("La contraseña no debe exceder los 255 caracteres");
        }
    }
}
