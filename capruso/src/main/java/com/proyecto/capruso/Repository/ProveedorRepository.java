package com.proyecto.capruso.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto.capruso.Entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, String> {

    @Query(value = "SELECT * FROM Proveedores WHERE ruc LIKE %?1% OR razon LIKE %?1% OR celular LIKE %?1% OR direccion LIKE %?1%", nativeQuery = true)
    List<Proveedor> buscartodo(String dato);

    @Query(value = "SELECT * FROM Proveedores ORDER BY ruc ASC", nativeQuery = true)
    List<Proveedor> OrdenarAscendente();

    @Query(value = "SELECT * FROM Proveedores ORDER BY ruc DESC", nativeQuery = true)
    List<Proveedor> OrdenarDescendente();
}