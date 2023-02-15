package com.proyecto.capruso.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.proyecto.capruso.Entity.Ingreso;
import com.proyecto.capruso.Repository.IngresoRepository;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository data;

    public List<Ingreso> listar() {
        return (List<Ingreso>) data.findAll();
    }

    public Optional<Ingreso> consultarcodigo(String codigo) {
        return data.findById(codigo);
    }

    public void guardar(Ingreso x) {
        data.save(x);
    }

    public void eliminar(String codigo) {
        data.deleteById(codigo);
    }

    public List<Ingreso> buscar(String dato) {
        return data.buscartodo(dato);
    }

    public List<Ingreso> OrdenarAscendente() {
        return data.OrdenarAscendente();
    }

    public List<Ingreso> OrdenarDescendente() {
        return data.OrdenarDescendente();
    }

    public Page<Ingreso> getAll(Pageable pageable) {
        return data.findAll(pageable);
    }
}
