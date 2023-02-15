package com.proyecto.capruso.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.proyecto.capruso.Entity.Producto;
import com.proyecto.capruso.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository data;

    public List<Producto> listar() {
        return (List<Producto>) data.findAll();
    }

    public Optional<Producto> consultarcodigo(String codigo) {
        return data.findById(codigo);
    }

    public void guardar(Producto x) {
        data.save(x);
    }

    public void eliminar(String codigo) {
        data.deleteById(codigo);
    }

    public List<Producto> buscar(String dato) {
        return data.buscartodo(dato);
    }

    public List<Producto> OrdenarAscendente() {
        return data.OrdenarAscendente();
    }

    public List<Producto> OrdenarDescendente() {
        return data.OrdenarDescendente();
    }

    public Page<Producto> getAll(Pageable pageable) {
        return data.findAll(pageable);
    }
}
