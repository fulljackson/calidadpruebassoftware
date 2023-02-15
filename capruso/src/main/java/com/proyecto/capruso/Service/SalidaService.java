package com.proyecto.capruso.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.proyecto.capruso.Entity.Salida;
import com.proyecto.capruso.Repository.SalidaRepository;

@Service
public class SalidaService {

    @Autowired
    private SalidaRepository data;

    public List<Salida> listar() {
        return (List<Salida>) data.findAll();
    }

    public Optional<Salida> consultarcodigo(String codigo) {
        return data.findById(codigo);
    }

    public void guardar(Salida x) {
        data.save(x);
    }

    public void eliminar(String codigo) {
        data.deleteById(codigo);
    }

    public List<Salida> buscar(String dato) {
        return data.buscartodo(dato);
    }

    public List<Salida> OrdenarAscendente() {
        return data.OrdenarAscendente();
    }

    public List<Salida> OrdenarDescendente() {
        return data.OrdenarDescendente();
    }

    public Page<Salida> getAll(Pageable pageable) {
        return data.findAll(pageable);
    }
}
