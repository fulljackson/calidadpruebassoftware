package com.proyecto.capruso.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.proyecto.capruso.Entity.Usuario;
import com.proyecto.capruso.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository data;

    public List<Usuario> listar() {
        return (List<Usuario>) data.findAll();
    }

    public Optional<Usuario> consultardni(String dni) {
        return data.findById(dni);
    }

    public void guardar(Usuario x) {
        data.save(x);
    }

    public void eliminar(String dni) {
        data.deleteById(dni);
    }

    public List<Usuario> buscar(String dato) {
        return data.buscartodo(dato);
    }

    public List<Usuario> OrdenarAscendente() {
        return data.OrdenarAscendente();
    }

    public List<Usuario> OrdenarDescendente() {
        return data.OrdenarDescendente();
    }

    public Page<Usuario> getAll(Pageable pageable) {
        return data.findAll(pageable);
    }

    public Usuario login(String username, String password) {
        Usuario usuario = data.findByUsernameAndPassword(username, password);
        return usuario;
    }
}
