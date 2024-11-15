package citas.service.imple;

import citas.entity.Usuarios;
import citas.exception.GeneralException;
import citas.exception.NoDataFoundException;
import citas.exception.ValidateException;
import citas.repository.UsuarioRepository;
import citas.service.UsuarioService;
import citas.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImple implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de usuarios");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralException("Error al obtener la lista de usuarios");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios findByUsername(String username) {
        try {
            Usuarios usuario = repository.findByUsername(username);
            if (usuario == null) {
                throw new NoDataFoundException("No se encontró un usuario con el nombre de usuario proporcionado");
            }
            return usuario;
        } catch (Exception e) {
            throw new GeneralException("Error al buscar usuario por nombre de usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un usuario con ese ID"));
        } catch (Exception e) {
            throw new GeneralException("Error al buscar usuario por ID");
        }
    }

    @Override
    @Transactional
    public Usuarios save(Usuarios usuario) {
        try {
            UsuarioValidator.save(usuario);

            // Verificar si el username ya existe
            Usuarios existingUser = repository.findByUsername(usuario.getUsername());
            if (existingUser != null && existingUser.getIdUsuario() == usuario.getIdUsuario()) {
                throw new ValidateException("El correo username ya está en uso");
            }
            // Actualizar registro
            if (usuario.getIdUsuario() != 0) {
                Usuarios existing = repository.findById(usuario.getIdUsuario())
                        .orElseThrow(() -> new NoDataFoundException("No existe un usuario con ese ID"));
                existing.setUsername(usuario.getUsername());
                existing.setPassword(usuario.getPassword());
                return repository.save(existing);
            }

            // Nuevo registro
            return repository.save(usuario);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralException("Error al guardar el usuario");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Usuarios usuario = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un usuario con ese ID"));
            repository.delete(usuario);
        } catch (Exception e) {
            throw new GeneralException("Error al eliminar el usuario");
        }
    }
}
