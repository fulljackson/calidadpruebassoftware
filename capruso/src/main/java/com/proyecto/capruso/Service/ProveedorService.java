package com.proyecto.capruso.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.capruso.Entity.Proveedor;
import com.proyecto.capruso.Repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository data;

    public List<Proveedor> listar() {
        return (List<Proveedor>) data.findAll();
    }

    public Optional<Proveedor> consultarruc(String ruc) {
        return data.findById(ruc);
    }

    public void guardar(Proveedor x) {
        data.save(x);
    }

    public void eliminar(String ruc) {
        data.deleteById(ruc);
    }

    public List<Proveedor> buscar(String dato) {
        return data.buscartodo(dato);
    }

    public List<Proveedor> OrdenarAscendente() {
        return data.OrdenarAscendente();
    }

    public List<Proveedor> OrdenarDescendente() {
        return data.OrdenarDescendente();
    }

    public Page<Proveedor> getAll(Pageable pageable) {
        return data.findAll(pageable);
    }
}