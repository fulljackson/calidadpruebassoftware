package com.proyecto.capruso.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto.capruso.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    
    @Query(value = "SELECT * FROM Productos WHERE codigo LIKE %?1% OR descripcion LIKE %?1% OR costo LIKE %?1% OR venta LIKE %?1% OR stock LIKE %?1% OR proveedor LIKE %?1%", nativeQuery = true)
    List<Producto> buscartodo(String dato);

    @Query(value = "SELECT * FROM Productos ORDER BY codigo ASC", nativeQuery = true)
    List<Producto> OrdenarAscendente();

    @Query(value = "SELECT * FROM Productos ORDER BY codigo DESC", nativeQuery = true)
    List<Producto> OrdenarDescendente();
}
