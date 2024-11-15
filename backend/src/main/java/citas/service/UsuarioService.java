package citas.service;

import citas.entity.Usuarios;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    public List<Usuarios> findAll(Pageable page);
    public List<Usuarios> findAll();
    public Usuarios findByUsername(String username);
    public Usuarios findById(int id);
    public Usuarios save(Usuarios usuario);
    public void delete(int id);
}
